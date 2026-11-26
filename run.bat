::macro pra iniciar os programas 100% vibecodado :D

@echo off
setlocal EnableDelayedExpansion
pushd "%~dp0"

if /I "%~1"=="client" goto client
if /I "%~1"=="server" goto server
if /I "%~1"=="both" goto both
if not "%~1"=="" goto invalid

echo 1. Iniciar o Coordenador (cliente)
echo 2. Iniciar o Trabalhador (servidor)
echo 3. Iniciar ambos localmente
set /p "MODE=Escolha 1, 2 ou 3: "
if "%MODE%"=="1" goto client
if "%MODE%"=="2" goto server
if "%MODE%"=="3" goto both
goto invalid

:client
call :build
if errorlevel 1 goto end
set /p "N=Digite N (inteiro positivo): "
set /p "WORKERS=Digite os enderecos dos trabalhadores (host:porta, separados por espacos): "
java -cp build\Coordenador Coordenador %N% %WORKERS%
goto end

:server
call :build
if errorlevel 1 goto end
set /p "PORT=Digite a porta do Trabalhador [3001]: "
if not defined PORT set "PORT=3001"
java -cp build\Trabalhador Trabalhador %PORT%
goto end

:both
call :build
if errorlevel 1 goto end
set /p "N=Digite N (inteiro positivo): "
set /p "WORKER_COUNT=Quantos trabalhadores deseja iniciar? [3]: "
if not defined WORKER_COUNT set "WORKER_COUNT=3"
set /p "PORT=Digite a porta inicial [3001]: "
if not defined PORT set "PORT=3001"
set /a CURRENT_PORT=PORT
set "WORKER_ADDRESSES="
for /L %%I in (1,1,%WORKER_COUNT%) do (
    start "Trabalhador na porta !CURRENT_PORT!" cmd /k "java -cp build\Trabalhador Trabalhador !CURRENT_PORT!"
    set "WORKER_ADDRESSES=!WORKER_ADDRESSES! localhost:!CURRENT_PORT!"
    set /a CURRENT_PORT+=1
)
timeout /t 2 /nobreak >nul
java -cp build\Coordenador Coordenador %N% !WORKER_ADDRESSES!
goto end

:build
if not exist build mkdir build
if not exist build\Trabalhador mkdir build\Trabalhador
if not exist build\Coordenador mkdir build\Coordenador
javac -d build\Trabalhador Trabalhador\*.java
if errorlevel 1 exit /b 1
javac -d build\Coordenador Coordenador\*.java
if errorlevel 1 exit /b 1
exit /b 0

:invalid
echo Opcoes: run.bat [client^|server^|both]
echo Execute sem argumentos para escolher no menu.

:end
popd
endlocal
