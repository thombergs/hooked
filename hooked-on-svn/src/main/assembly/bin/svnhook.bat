@echo off

REM 1. Set the environment variable %HOOKED_ON_SVN_HOME% to the path where you
REM    installed the hooked-on-svn distributable.

REM 2. Create a script named "post-commit.bat" in the hooks folder of your SVN repositoryUrl
REM    with the following line:
REM    call %HOOKED_ON_SVN_HOME%/bin/svnhook.bat %1 %2 <URL_OF_YOUR_REPOSITORY>

set CMD_LINE_ARGS=

set PLUGINS_PATH=%HOOKED_ON_SVN_HOME%/plugins
set LIB_PATH=%HOOKED_ON_SVN_HOME%/lib
set BIN_PATH=%HOOKED_ON_SVN_HOME%/bin
set LOG_PATH=%HOOKED_ON_SVN_HOME%/log
set CONF_PATH=%HOOKED_ON_SVN_HOME%/conf
set CLASSPATH=%LIB_PATH%/*;%PLUGINS_PATH%/*;
set EXECUTABLE_PATH=%BIN_PATH%/svnhook.bat
set MAIN_CLASS=org.wickedsource.hooked.svn.SvnHook

if exist %EXECUTABLE_PATH% goto javaOpts
echo The environment variable HOOKED_ON_SVN_HOME is not defined correctly.
echo Please point this variable to the path where you installed the SVN Hook.
goto end

:javaOpts
set JAVA_OPTS=
set JAVA_OPTS=%JAVA_OPTS% -Dlogback.configurationFile=%CONF_PATH%/logback.xml
set JAVA_OPTS=%JAVA_OPTS% -Dhooked.configPath=%CONF_PATH%

:setArgs
if ""%1""=="""" goto doneSetArgs
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setArgs

:doneSetArgs
cd %HOOKED_ON_SVN_HOME%
java %JAVA_OPTS% -classpath "%CLASSPATH%" %MAIN_CLASS% %CMD_LINE_ARGS% >> %LOG_PATH%/svnhook.log

:end