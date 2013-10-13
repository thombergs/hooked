@echo off

if exist "%HOOKED_ON_SVN_HOME%\bin\svnhook.bat" goto setArgs
echo The environment variable HOOKED_ON_SVN_HOME is not defined correctly.
echo Please point this variable to the path where you installed the SVN Hook.
goto end

set CMD_LINE_ARGS=
:setArgs
if ""%1""=="""" goto doneSetArgs
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setArgs
:doneSetArgs

echo %CMD_LINE_ARGS%

:ok
java -jar %HOOKED_ON_SVN_HOME%/lib/hooked-on-svn-1.0-SNAPSHOT.jar %CMD_LINE_ARGS%

:end