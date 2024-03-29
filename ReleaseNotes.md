# Release Notes

This file contains the release notes for the SmartMDSD Toolchain version 3.x. For each release, it provides important information to users to support them in switching from one specific version the previous version. This includes information and instructions how to migrate content from one version to another.

## v3.19

This is a maintenance release with a small upgrade of the component code generator. The projects developed with a previous version can be used with the newer version. In case you want to use the new active input handlers, simply trigger the code generation for the respective component project and build the component binary.

## v3.18

This release updates the Eclipse base installation from previously 2020-09 to the newest Eclipse **2021-12** (aka Eclipse **4.22**). While there should not be any major/apparent changes in the UI, there might be some improvements in the overall behavior and GUIs as all the standard Eclipse plugins have been updated to their newest stable versions. If you are interested in the general Eclipse updates, please fillow this link: [Eclipse IDE 2021-12](https://www.eclipse.org/eclipseide/2021-12/).

You can use your old Eclipse workspaces. When you load an old workspace with the new Eclipse version, a popup window will warn that the workspace has been created with an older version and ask if you like to update it the new version. Please accept this request (it should work for most old workspaces), or create a new workspace if you want to keep the old workspace ungchanged (as there is no easy way to revert the update).

Other than that, this release consist of several internal bug fixes, specifically within the code generators for DomainModels, which should be fully compatible with projects created with older versions. Just execute the code generator for your old projects, and you are good to go.

An annoying bug has been fixed where newly created models have initially opened in read-only mode, particularly when creating a new robotics project.

## v3.17

This release does not require any user intervention. All changes are purely internal and it is not required to modify projects created with a previous release.

The SmartMDSD Build Console has been refined. It now displays both outputs - from the CMake run and from the build run - combined in the same console window (previously there were two separate windows, that had to be switched manually). This is a small yet useful quality-of-life improvement.

## v3.16

This release does not require any user intervention. All changes are purely internal and neither is it required to modify projects created with a previous release, nor are there any changes in the user interface.

## v3.15

This release is mostly concerned around a few bug-fixes and some internal restructuring (including splitting the main feature into two features). At the user-interface these changes are not visible though.

## v3.14

This is the first release under the Eclipse Foundation roof. While the main functionality remained the same as of release v3.13, the plugins have been renamed following the guidelines from the Eclipse Project Handbook:

https://www.eclipse.org/projects/handbook/

Moreover, the maven-based build infrastructure has been set-up so that the SmartMDSD plugins can now be built within the Eclipse Common Build Infrastructure (CBI) as described here:

https://wiki.eclipse.org/CBI

## v3.13

This update only affects the code-generation for ROS Mixedport components. There, the user-level generation of Activity classes now support generation of additional ROS-related includes and class-member definitions. So if you are using ROS in your components, then we suggest you to backup your implementation of the Activities in your Component (i.e. the Activity classes in the smartsoft/src folder), to temporarily rename the Activity's header and source files and to trigger the Component's code genration by using the menu **SmartMDSD** -> **Run Code-Generation**. After that, merge the contents of the newly generated files with your original files so that the new extensions are included.

## v3.12

This is a hotfix release where several internal code generation bugs are fixed. We recommend to manually trigger the code generation for your existing projects by selecting the project in the SmartMDSD Project Explorer and using the Eclipse menu: **SmartMDSD** -> **Run Code-Generation**. This will update your src-gen folders so you also might want rebuilding your projects by using the Eclipse menu **SmartMDSD** -> **Build Project** (alternatively, you can use the Eclipse menu: **Project** -> **Build All** to rebuild all your projects in the workspace at once). There are no other changes in the models or user-code required (other than those mentioned in release v3.11).

## v3.11

This is a maintainance release mostly motivated by the updated background infrastructure. Most notably, the **SmartSoft Component Developer API** has been updated from version 1.0 to **version 1.1**, and the **ACE/SmartSoft Kernel** has been updated from version 3.1 to **version 3.2**. Therefore the Toolchain's internal C++ code generators have been updated and now generate consistently towards the updated background infrastructure. This also means that if you have not yet updated your current background infrastructure, then you should do so **before** using this new Toolchain update.

The majority of changes are hidden within the internal src-gen folders. The only two visible updates within the use code relate to the component code generation, where the **QueryHandlers** and **EventHandler** need to be manually re-generated. In order to do so, follow the following four steps procedure:

1. In your component project's subfolder "smartsoft/src": backup your implementation of the QueryHandlers and EventHandler (if you use any).
2. Trigger code generation using the Eclipse menu: **SmartMDSD** -> **Run Code-Generation**
3. merge the new generated handler classes with your backed up implementation (don't override the newly generated class interface)
4. Rebuild the project using the Eclipse menu: **SmartMDSD** -> **Build Project**

As pre-announced in the previous release, the **old UI now has been deactivated** in this release. This means, that **from now on you have to use the new UI** by opening the new Eclipse perspectives introduced in the release v3.9 (see release notes below).

The SmartMDSD Preferences page has been revised (see the menu: **Window -> Preferences -> SmartMDSD**). Here you can now also specify the default **build type Debug/Release** which will be used to either include debug symbols into the binaries (default), or exclude it to reduce the overall footprint. Moreover, here you can select the used **ROS distribution for building ROS mixed port components**. The toolchain initially searches the default location **/opt/ros** and selects the first found distribution. In case you have more than one, here you can change the required distro. The Toolchain build process automatically sets related ROS environment variables as described here (see "4. Building projects inside Eclipse"):

[ROS Wiki - IDEs](http://wiki.ros.org/IDEs)

Please note, the ROS build feature is rather new/experimental and might not yet work in all situations. In case it does not work, as a backup strategy, you can compile the project from within a bash terminal with the ROS setup initiated from .bashrc.

This release also includes a few minor usability improvements. Most notably, recursive build now works from within the Toolchain with a considerable improvement of the overall build performance: each dependency now is only built once (and not multiple times as before). While you still can build projects from a bash terminal if needed, the Toolchain build now is the recommended way.

## v3.10

This is a minor release, mostly focusing on bugfixing and smaller usability improvements. The biggest change concerns the Plain OPC UA usage which now requires the new **[Open62541CppWrapper library](https://github.com/Servicerobotics-Ulm/Open62541CppWrapper)** to be installed. Moreover, the Welcome page has been extended with new tutorials, links and cheat-sheets (use the menu Help->Welcome, or Help->Cheat sheets...). Finally, a new context menu has been added: when right-clicking on any folder within the SmartMDSD Project Explorer, you now can open this folder directly within a Linux terminal. Other than tha, all the previous major features from the last release v3.9 remain unchanged. Please note, that the old UI (mentioned in v3.9 below) is curretly still active, however, it will be likely removed in one of the next releases, so we encourage using the new UI from now on and to update your projects as described below in the v3.9 section.

## v3.9

This release provides a **major User Interface (UI) update**. While the old UI still can be used as before, it is considered deprecated as of this release, and it will be removed in one of the following releases as soon as the new UI has been proved to be stable and robust enough in the field. While the new UI has cleaned-up and harmonized all the context menus and commands, the basic functionality is still there and for the existing projects no major changes are required (besides of adding a new project nature as described below). You can also continue to use your existing workspaces. In order to use the full power of the new UI, you just have to configure one of the **new SmartMDSD project natures** for each of your existing projects. In this release, four dedicated project natures exist: DomainModels -, Component -, Behavior -, and System Project Nature. You can configure one of these natures as follows:

1) **Right-click on your project** within the Model Explorer window (typically located on the upper-left)
2) Select the context menu **Configure**
3) Now click on one of the new commands, depending on your currently selected project type:
- For DomainModels projects select: **Configure SmartMDSDS DomainModels nature...**
- For Component projects select: **Configure SmartMDSDS Component nature...**
- For Behavior projects select: **Configure SmartMDSDS Behavior nature...**
- For System projects select: **Configure SmartMDSDS System nature...**

Please note that you can revert this step at any time. After having configured one of these natures, the previous configure command changes automatically from "**Configure** SmartMDSD ..." to "**Remove** SmartMDSD ...". Naturally, by selecting one of the "Remove" commands the project nature gets removed from that project. Please also note that all the new features of the new UI will first be usable if you have configured one of SmartMDSD project natures.

Now, the next step to do is to open one of the four **new Eclipse Perspectives**. Each of these perspectives reconfigures the visible windows and context menus depending on your current task to do. In this release, the following perspectives are realized for the four core **RobMoSys developer roles**:

- **Domain Expert (Tier 2)**
- **Component Supplier (Tier 3)**
- **Behavior Developer (Tier 3)**
- **System Builder (Tier 3)**

For more information on the RobMoSys roles, please check the **RobMoSys Technical Wiki**: https://robmosys.eu/wiki/general_principles:ecosystem:roles

If you haven't opened these perspective before, you can do so as follows:

1) Select Eclipse menu: **Window** -> **Perspective** -> **Open Perspective** -> **Other...**
2) A new popup window named "Open Perspective" should show up; here you can select one of the new perspectives named after the respective RobMoSys role (see above) 
3) Finally, push the **Open** button

You will immediately notice that the "Model Explorer" on the left is exchanged by the new **SmartMDSD Project Explorer**, which is specifically configured for each of the RobMoSys roles and by default, you will only see related projects (depending on their configured project nature). Moreover, you will see new context menus and the arrangement of the visible windows will change if you switch between the different perspectives. After you have opened each of the new perspectives once, a shortcut for each of them will be visible on the upper right corner of the Eclipse main window and you can easily switch between them. Please note, you can always go back to the default "Modeling" perspective in case you either want to use the old menus, or in case you need to open some old projects (created with one of the preceding SmartMDSD Toolchain releases) that do not yet have one of the new project natures configured.

Another improvement in the UI is related to building (i.e. compiling) the projects. In the previous versions there were two commands, CMake and Make that had to be called manually in the right order. In the new UI these two commands have been combined into one build command which now is even integrated into the overall Eclipse build infrastructure. This means that a project can now be built simply by using the standard Eclipse menu: **Project** -> **Build Project** (or **Build All** to build all projects in the current workspace). Analogously, the standard menu: **Project** -> **Clean...** will clean the selected projects.

This release provides the integration of robotics behavior development into the SmartMDSD Toolchain.
This includes so far the following parts: Behavior Task Level modeling, with dedicated projects. Behavior Skill level modeling with dedicated tooling associated to the role of the Component Supplier. The modeling of skill and behavior task interfaces associated to the role of the Domain Experts. The modeling and realization of Behavior Coordination Interfaces and Services which are used by coordination components (Sequencer).

## v3.8

This release does not involve any changes for the existing projects created with the previous SmartMDSD Toolchain v3.7.


## v3.7

* In previous versions of the Toolchain, the Java Classpath of each project required a dependency to two libraries
  * ECLIPSE_HOME/plugins/org.xtext.base.stateMachine_3.4.0.jar
  * ECLIPSE_HOME/plugins/org.xtext.service.parameterDefinition_3.4.0.jar
* Since version 3.7 these libraries do not exists anymore. If you have an old project please remove these library dependencies from your project's java classpath. You can do this either by opening the project properties in Eclipse (right click on the project within the Project Explorer, select **Properties -> Java Build Path -> Libraries** and delete the conflicting dependencies here). Or just open the hidden **.classpath** file in the project folder and delete the two classpath entries mentioned above.

* It is now recommended to deactivate the global (automatic) C++ indexer, which will consume all your memory for projects with heavy dependencies (such as e.g. ROS). You can still trigger the indexer for individual projects manually, but do not activate the automatic indexer update.
  * To deactivate the automatic indexer for your workspace, go to **Window -> Preferences -> C/C++ -> Indexer** and **deactivate the option "Automatically update the index"** (this option is highlighted with black color for a reason as it seems to be an evil option ;-)).
  * To manually trigger the C++ indexer for your project, **right-click on your project** within the project explorer and select **Index -> Rebuild**.
If you used the new Eclipse Installer to install the SmartMDSD Toolchain, then the global index update should be automatically deactivated by default.
