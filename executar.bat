@echo off
setlocal EnableDelayedExpansion
pushd "%~dp0"

if /I "%~1"=="coordenador" goto coordenador
if /I "%~1"=="client" goto coordenador
if /I "%~1"=="trabalhador" goto trabalhador
if /I "%~1"=="server" goto trabalhador
if /I "%~1"=="ambos" goto ambos
if /I "%~1"=="both" goto ambos
if not "%~1"=="" goto invalido

echo 1. Iniciar o Coordenador
echo 2. Iniciar um Trabalhador
echo 3. Iniciar ambos localmente
set /p "MODO=Escolha 1, 2 ou 3: "
if "%MODO%"=="1" goto coordenador
if "%MODO%"=="2" goto trabalhador
if "%MODO%"=="3" goto ambos
goto invalido

:coordenador
call :verificar
if errorlevel 1 goto fim
set /p "N=Digite N (inteiro positivo): "
set /p "TRABALHADORES=Digite os enderecos host:porta separados por espacos: "
if not defined N goto entrada_invalida
if not defined TRABALHADORES goto entrada_invalida
java -cp build\Coordenador Coordenador %N% %TRABALHADORES%
goto fim

:trabalhador
call :verificar
if errorlevel 1 goto fim
set /p "PORTA=Digite a porta do Trabalhador [3001]: "
if not defined PORTA set "PORTA=3001"
java -cp build\Trabalhador Trabalhador %PORTA%
goto fim

:ambos
call :verificar
if errorlevel 1 goto fim
set /p "N=Digite N (inteiro positivo): "
set /p "QUANTIDADE=Quantos trabalhadores iniciar? [3]: "
if not defined QUANTIDADE set "QUANTIDADE=3"
set /p "PORTA=Digite a porta inicial [3001]: "
if not defined PORTA set "PORTA=3001"
if not defined N goto entrada_invalida
set /a QUANTIDADE=QUANTIDADE, PORTA=PORTA >nul 2>&1
if errorlevel 1 goto entrada_invalida
if %QUANTIDADE% LEQ 0 goto entrada_invalida

set "ENDERECOS="
set /a PORTA_ATUAL=PORTA
for /L %%I in (1,1,%QUANTIDADE%) do (
    start "Trabalhador na porta !PORTA_ATUAL!" cmd /k "java -cp build\Trabalhador Trabalhador !PORTA_ATUAL!"
    set "ENDERECOS=!ENDERECOS! localhost:!PORTA_ATUAL!"
    set /a PORTA_ATUAL+=1
)
timeout /t 2 /nobreak >nul
java -cp build\Coordenador Coordenador %N% !ENDERECOS!
goto fim

:verificar
java -version >nul 2>&1
if errorlevel 1 (
    echo Erro: java nao encontrado. Instale o JDK e configure o PATH.
    exit /b 1
)
if not exist build\Trabalhador\Trabalhador.class (
    echo Erro: arquivos compilados nao encontrados. Execute compilar.bat primeiro.
    exit /b 1
)
if not exist build\Coordenador\Coordenador.class (
    echo Erro: arquivos compilados nao encontrados. Execute compilar.bat primeiro.
    exit /b 1
)
exit /b 0

:entrada_invalida
echo Erro: informe os valores solicitados.
goto fim

:invalido
echo Uso: executar.bat [coordenador^|trabalhador^|ambos]
echo Execute sem argumentos para escolher no menu.

:fim
popd
endlocal
