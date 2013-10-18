@echo off

REM 1. Set the environment variable %HOOKED_ON_SVN_HOME% to the path where you
REM    installed the hooked-on-svn distributable.

REM 2. Create a script named "post-commit.bat" in the hooks folder of your SVN repository
REM    with the following line:
REM    call %HOOKED_ON_SVN_HOME%/bin/svnhook.bat

set CMD_LINE_ARGS=

set PLUGINS_PATH=%HOOKED_ON_SVN_HOME%/plugins
set LIB_PATH=%HOOKED_ON_SVN_HOME%/lib
set BIN_PATH=%HOOKED_ON_SVN_HOME%/bin
set CLASSPATH=%LIB_PATH%/*;%PLUGINS_PATH%/*;
set MAIN_CLASS=org.wickedsource.hooked.svn.SvnHook

call %BIN_PATH%/config.bat

echo using classpath %CLASSPATH%

if exist %JAR_PATH% goto jarFound
echo JAR not found at %JAR_PATH%!
echo The environment variable HOOKED_ON_SVN_HOME is not defined correctly.
echo Please point this variable to the path where you installed the SVN Hook.
goto end

:jarFound
echo Found JAR at %JAR_PATH%.
echo Using plugins found at %PLUGINS_PATH%.
goto setArgs

:setArgs
if ""%1""=="""" goto doneSetArgs
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setArgs

:doneSetArgs
cd %HOOKED_ON_SVN_HOME%
java -classpath "%CLASSPATH%" %MAIN_CLASS% %CMD_LINE_ARGS%

:end