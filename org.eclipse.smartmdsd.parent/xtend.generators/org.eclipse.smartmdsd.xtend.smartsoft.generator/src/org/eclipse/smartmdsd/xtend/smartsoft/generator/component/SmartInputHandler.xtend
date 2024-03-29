/********************************************************************************
 * Copyright (c) 2018 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
 * headed by Prof. Dr. Christian Schlegel
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Alex Lotz, Matthias Lutz, Dennis Stampfer
 ********************************************************************************/
package org.eclipse.smartmdsd.xtend.smartsoft.generator.component

import com.google.inject.Inject
import org.eclipse.smartmdsd.xtend.smartsoft.generator.CopyrightHelpers
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.smartmdsd.xtend.smartsoft.generator.ExtendedOutputConfigurationProvider
import org.eclipse.smartmdsd.xtend.smartsoft.generator.commObj.CommObjectGenHelpers
import org.eclipse.smartmdsd.ecore.component.componentDefinition.InputHandler
import org.eclipse.smartmdsd.ecore.component.componentDefinition.InputPort
import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinition

import static extension org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinitionModelUtility.*

class SmartInputHandler {
	@Inject extension ComponentGenHelpers
	@Inject extension CommObjectGenHelpers
	@Inject extension CopyrightHelpers
	@Inject extension InteractionObserver
	
	def InputHandlerHeaderFileName(InputHandler h) { h.nameClass + "Core.hh" }
	def InputHandlerSourceFileName(InputHandler h) { h.nameClass + "Core.cc" }
	def InputHandlerUserHeaderFileName(InputHandler h) { h.nameClass + ".hh" }
	def InputHandlerUserSourceFileName(InputHandler h) { h.nameClass + ".cc" }
	
	def UpcallInterfaceHeaderFileName(InputPort p) { p.nameClass + "UpcallInterface.hh" }
	def UpcallManagerHeaderFileName(InputPort p) { p.nameClass + "UpcallManager.hh" }
	def UpcallManagerSourceFileName(InputPort p) { p.nameClass + "UpcallManager.cc" }
	
	def void CreateSmartInputHandlers(ComponentDefinition component, IFileSystemAccess2 fsa) {
		for(input: component.elements.filter(InputPort)) {
			// generate abstract UpcallInterface
			fsa.generateFile(input.UpcallInterfaceHeaderFileName, input.UpcallInterfaceHeaderFileContent)
			// generate the UpcallManager class
			fsa.generateFile(input.UpcallManagerHeaderFileName, input.UpcallManagerHeaderFileContent)
			fsa.generateFile(input.UpcallManagerSourceFileName, input.UpcallManagerSourceFileContent)
		}
		
		for(handler: component.elements.filter(InputHandler)) {
			fsa.generateFile(handler.InputHandlerHeaderFileName, handler.InputHandlerHeaderFileContent)
			fsa.generateFile(handler.InputHandlerSourceFileName, handler.InputHandlerSourceFileContent)
	
			fsa.generateFile(handler.InputHandlerUserHeaderFileName, ExtendedOutputConfigurationProvider::SRC_OUTPUT, handler.TaskUserHeaderFileContent)		
			fsa.generateFile(handler.InputHandlerUserSourceFileName, ExtendedOutputConfigurationProvider::SRC_OUTPUT, handler.InputHandlerUserSourceFileContent)
		}
	}
	
	/////////////////////////////////////////////////////
	// <InpuPort>UpcallInterface.hh
	/////////////////////////////////////////////////////
	def UpcallInterfaceHeaderFileContent(InputPort port) '''
		«getCopyright()»
		#ifndef _«port.name.toUpperCase»_UPCALL_INTERFACE_HH
		#define _«port.name.toUpperCase»_UPCALL_INTERFACE_HH
		
		«FOR obj: port.communicationObjects.entrySet.sortBy[it.value.name]»
		#include "«obj.value.userClassHeaderFileNameFQN»"
		«ENDFOR»
		
		class «port.nameClass»UpcallInterface {
		public:
			virtual ~«port.nameClass»UpcallInterface() {  }
		
			virtual void on_«port.name»(const «port.inputHandlerCommObject» &input) = 0;
		};
		
		#endif
	'''
	
	/////////////////////////////////////////////////////
	// <InpuPort>UpcallManager.hh
	/////////////////////////////////////////////////////
	def UpcallManagerHeaderFileContent(InputPort port) '''
		«getCopyright()»
		#ifndef _«port.name.toUpperCase»_UPCALL_MANAGER_HH
		#define _«port.name.toUpperCase»_UPCALL_MANAGER_HH
		
		#include <list>
		#include "aceSmartSoft.hh"
		#include "«port.UpcallInterfaceHeaderFileName»"
		
		/** «port.nameClass»UpcallManager connects input-handling with Upcall propagation
		 *
		 * This class implements an InputHandler for the InputPort «port.name» and propagates the handling 
		 * of incoming data to all associated (i.e. attached) Upcalls.
		 */
		class «port.nameClass»UpcallManager
		:	public Smart::IInputHandler<«port.inputHandlerCommObject»>
		{
		private:
			// list of associated updalls
			std::list<«port.nameClass»UpcallInterface*> upcalls;
		
			// call the on_«port.name» of all the attached «port.nameClass»UpcallInterfaces
			void notify_upcalls(const «port.inputHandlerCommObject» &input);
			
		protected:
			virtual void handle_input(const «port.inputHandlerCommObject» &input) {
				// relay input-handling to all attached «port.nameClass»UpcallInterfaces
				this->notify_upcalls(input);
			}
		public:
			«port.nameClass»UpcallManager(
				Smart::InputSubject<«port.inputHandlerCommObject»> *subject,
				const int &prescaleFactor=1
			);
			virtual ~«port.nameClass»UpcallManager();
			
			void attach(«port.nameClass»UpcallInterface *upcall);
			void detach(«port.nameClass»UpcallInterface *upcall);
		};
		
		#endif
	'''
	
	///////////////////////////
	// InputHandler source name
	///////////////////////////
	def UpcallManagerSourceFileContent(InputPort port) '''
		«getCopyright()»
		#include "«port.UpcallManagerHeaderFileName»"
		
		«port.nameClass»UpcallManager::«port.nameClass»UpcallManager(
			Smart::InputSubject<«port.inputHandlerCommObject»> *subject,
			const int &prescaleFactor)
			:	Smart::IInputHandler<«port.inputHandlerCommObject»>(subject, prescaleFactor)
		{  }
		«port.nameClass»UpcallManager::~«port.nameClass»UpcallManager()
		{  }
		
		void «port.nameClass»UpcallManager::notify_upcalls(const «port.inputHandlerCommObject» &input)
		{
			for(auto it=upcalls.begin(); it!=upcalls.end(); it++) {
				(*it)->on_«port.name»(input);
			}
		}
		
		void «port.nameClass»UpcallManager::attach(«port.nameClass»UpcallInterface *upcall)
		{
			upcalls.push_back(upcall);
		}
		void «port.nameClass»UpcallManager::detach(«port.nameClass»UpcallInterface *upcall)
		{
			upcalls.remove(upcall);
		}
	'''
	
	def private getInput(InputHandler handler) {
		return handler.inputPort
	}
	
	///////////////////////////
	// Header file of InputHandler: InputHandlerCore.hh
	///////////////////////////
	def InputHandlerHeaderFileContent(InputHandler handler) '''
		«getCopyright()»
		#ifndef _«handler.nameClass.toUpperCase»_CORE_HH
		#define _«handler.nameClass.toUpperCase»_CORE_HH
		
		#include <aceSmartSoft.hh>
		
		// include the main input-handler interface
		#include "«handler.input.UpcallInterfaceHeaderFileName»"
		// include all other input interfaces (if any)
		«FOR inLink: handler.inputLinks.sortBy[it.name]»
			#include "«inLink.inputPort.UpcallInterfaceHeaderFileName»"
		«ENDFOR»
		
		// include all interaction-observer interfaces
		#include <«handler.nodeObserverInterfaceHeaderFileName»>
		«FOR obs: handler.observers.sortBy[it.nameClass]»
		#include <«obs.subject.nodeObserverInterfaceHeaderFileName»>
		«ENDFOR»
		
		class «handler.nameClass»Core
		:	public Smart::InputTaskTrigger<«handler.input.inputHandlerCommObject»>
		«IF handler.activeQueue»
		,	public SmartACE::Task
		«ENDIF»
		«FOR obs: handler.observers.sortBy[it.nameClass]»
		,	public «obs.subject.nodeObserverInterfaceClassName»
		«ENDFOR»
		,	public «handler.input.nameClass»UpcallInterface
		«FOR inLink: handler.inputLinks.sortBy[it.name]»
		,	public «inLink.inputPort.nameClass»UpcallInterface
		«ENDFOR»
		{
		private:
			«FOR inLink: handler.inputLinks.sortBy[it.name]»
				Smart::StatusCode «inLink.inputPort.nameInstance»Status;
				«inLink.inputPort.inputHandlerCommObject» «inLink.inputPort.nameInstance»Object;
			«ENDFOR»
			
			virtual void updateAllCommObjects();
			
			«IF handler.activeQueue»
			// implementing active-queue handler
			std::atomic<bool> signalled_to_stop;
			std::mutex handler_mutex;
			std::condition_variable handler_cond_var;
			std::list<«handler.input.inputHandlerCommObject»> request_queue;
			// inputs are pushed to the request_queue
			virtual void handle_input(const «handler.input.inputHandlerCommObject»& input);
			// inputs are processed from within the thread, thus implementing an active FIFO request-queue
			virtual int task_execution() override;
			// override the default stop behavior to also release the active request queue
			virtual int stop(const bool wait_till_stopped=true) override
			{
				signalled_to_stop = true;
				handler_cond_var.notify_all();
				return SmartACE::Task::stop();
			}
			«ELSE»
			// internal input handling method
			virtual void handle_input(const «handler.input.inputHandlerCommObject»& input) {
				this->updateAllCommObjects();
				// call the input handler method (which has to be implemented in derived classes)
				this->on_«handler.input.name»(input);
				// notify all attached interaction observers
				this->notify_all_interaction_observers();
				// call implementation of base class
				Smart::InputTaskTrigger<«handler.input.inputHandlerCommObject»>::handle_input(input);
			}
			«ENDIF»
			
		«handler.compileNodeSubjectHeader»
			
		protected:
			«FOR obs: handler.observers.sortBy[it.name]»
			// overload this method in derived classes!
			virtual void on_update_from(const «obs.subject.nameClass»* subject) {
				// no-op
			}
			«ENDFOR»
		
			«FOR input: handler.inputLinks.map[inputPort].sortBy[it.name]»
				// overload and implement this method in derived classes to immediately get all incoming updates from «input.name» (as soon as they arrive)
				virtual void on_«input.name»(const «input.inputHandlerCommObject» &input) {
					// no-op
				}
				
				// this method can be safely used from the thread in derived classes
				inline Smart::StatusCode «input.nameInstance»GetUpdate(«input.inputHandlerCommObject» &«input.nameInstance»Object) const
				{
					// copy local object buffer and return the last status code
					«input.nameInstance»Object = this->«input.nameInstance»Object;
					return «input.nameInstance»Status;
				}
				
			«ENDFOR»
		public:
			«handler.nameClass»Core(
				Smart::InputSubject<«handler.input.inputHandlerCommObject»> *subject,
				const int &prescaleFactor=1);
			virtual ~«handler.nameClass»Core();
		};
		#endif
	'''
	
	///////////////////////////
	// InputHandler source name
	///////////////////////////
	def InputHandlerSourceFileContent(InputHandler handler) '''
		«getCopyright()»
		#include "«handler.InputHandlerHeaderFileName»"
		#include "«handler.InputHandlerUserHeaderFileName»"
		
		«handler.nameClass»Core::«handler.nameClass»Core(
			Smart::InputSubject<«handler.input.inputHandlerCommObject»> *subject,
			const int &prescaleFactor)
			:	Smart::InputTaskTrigger<«handler.input.inputHandlerCommObject»>(subject, prescaleFactor)
			«FOR inLink: handler.inputLinks.sortBy[it.name]»
				,	«inLink.inputPort.nameInstance»Status(Smart::SMART_DISCONNECTED)
				,	«inLink.inputPort.nameInstance»Object()
			«ENDFOR»
		{
			«IF handler.activeQueue»
				signalled_to_stop = false;
				this->start();
			«ENDIF»
		}
		«handler.nameClass»Core::~«handler.nameClass»Core()
		{  
			«IF handler.activeQueue»
				this->stop();
			«ENDIF»
		}
		
		«IF handler.activeQueue»
			// inputs are pushed to the request_queue
			void «handler.nameClass»Core::handle_input(const «handler.input.inputHandlerCommObject» &input) {
				std::unique_lock<std::mutex> scoped_lock(handler_mutex);
				request_queue.push_back(input);
				scoped_lock.unlock();
				
				handler_cond_var.notify_all();
			}
			// inputs are processed from within the thread, thus implementing an active FIFO request-queue
			int «handler.nameClass»Core::task_execution() {
				while(!signalled_to_stop) {
					std::unique_lock<std::mutex> scoped_lock(handler_mutex);
					if(request_queue.empty()) {
						handler_cond_var.wait(scoped_lock);
						if(signalled_to_stop)
							return 0;
					}
					«handler.input.inputHandlerCommObject» input = request_queue.front();
					request_queue.pop_front();
					scoped_lock.unlock();
					
					this->updateAllCommObjects();
					// call the input handler method (which has to be implemented in derived classes)
					this->on_«handler.input.name»(input);
					// notify all attached interaction observers
					this->notify_all_interaction_observers();
					// call implementation of base class
					Smart::InputTaskTrigger<«handler.input.inputHandlerCommObject»>::handle_input(input);
				}
				return 0;
			}
		«ENDIF»
		
		void «handler.nameClass»Core::updateAllCommObjects() {
			«FOR input: handler.inputLinks.map[inputPort].sortBy[it.name]»
				«input.nameInstance»Status = COMP->«input.nameInstance»InputTaskTrigger->getUpdate(«input.nameInstance»Object);
			«ENDFOR»
		}
		
		«handler.compileNodeSubjectSource»
	'''
	
	///////////////////////////
	// User InputHandler HEADER: InputHandler.hh
	///////////////////////////
	def TaskUserHeaderFileContent(InputHandler handler) '''
		«getCopyrightWriteOnce()»
		#ifndef _«handler.nameClass.toUpperCase»_HH
		#define _«handler.nameClass.toUpperCase»_HH
		
		#include "«handler.InputHandlerHeaderFileName»"
			
		class «handler.nameClass»  : public «handler.nameClass»Core
		{		
		public:
			«handler.nameClass»(Smart::InputSubject<«handler.input.inputHandlerCommObject»> *subject, const int &prescaleFactor=1);
			virtual ~«handler.nameClass»();
			
			virtual void on_«handler.input.name»(const «handler.input.inputHandlerCommObject» &input);
		};
		
		#endif
	'''
	
	///////////////////////////
	// User InputHandler source: InputHandler.cc
	///////////////////////////
	def InputHandlerUserSourceFileContent(InputHandler handler) '''
		«getCopyrightWriteOnce()»
		#include "«handler.InputHandlerUserHeaderFileName»"
		
		#include <iostream>
		
		«handler.nameClass»::«handler.nameClass»(Smart::InputSubject<«handler.input.inputHandlerCommObject»> *subject, const int &prescaleFactor)
		:	«handler.nameClass»Core(subject, prescaleFactor)
		{
			std::cout << "constructor «handler.nameClass»\n";
		}
		«handler.nameClass»::~«handler.nameClass»() 
		{
			std::cout << "destructor «handler.nameClass»\n";
		}
		
		void «handler.nameClass»::on_«handler.input.name»(const «handler.input.inputHandlerCommObject» &input)
		{
			// implement business logic here
			// (do not use blocking calls here, otherwise this might block the InputPort «handler.input.name»)
		}
	'''
}
