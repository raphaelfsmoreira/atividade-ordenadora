@echo off
setlocal
pushd "%~dp0"

javac -version >nul 2>&1
if errorlevel 1 (
    echo Erro: javac nao encontrado. Instale o JDK e configure o PATH.
    popd
    exit /b 1
)

if not exist build mkdir build
if not exist build\Trabalhador mkdir build\Trabalhador
if not exist build\Coordenador mkdir build\Coordenador

javac -d build\Trabalhador trabalhador\*.java
if errorlevel 1 goto erro

javac -d build\Coordenador coordenador\*.java
if errorlevel 1 goto erro

echo Compilacao concluida com sucesso.
popd
exit /b 0

:erro
echo Erro durante a compilacao.
popd
exit /b 1
