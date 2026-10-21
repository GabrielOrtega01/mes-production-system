# run.ps1 — Compila y ejecuta cualquier patron del MES
# Uso: .\run.ps1 singleton
#      .\run.ps1 decorator
#      .\run.ps1 list       (muestra los patrones disponibles)

param([string]$patron = "list")

$CP  = "target/classes;lib/junit-platform-console-standalone-1.10.3.jar"
$SRC = Get-ChildItem -Recurse -Filter "*.java" "src/main/java" | ForEach-Object { $_.FullName }

$PATRONES = @{
    "singleton"         = "com.fabricaviva.pruebas.PruebaSingleton"
    "factory"           = "com.fabricaviva.pruebas.PruebaFactoryMethod"
    "abstractfactory"   = "com.fabricaviva.pruebas.PruebaAbstractFactory"
    "builder"           = "com.fabricaviva.pruebas.PruebaBuilder"
    "prototype"         = "com.fabricaviva.pruebas.PruebaPrototype"
    "composite"         = "com.fabricaviva.pruebas.PruebaComposite"
    "decorator"         = "com.fabricaviva.pruebas.PruebaDecorator"
    "decoratorprof"     = "com.fabricaviva.pruebas.PruebaDecoratorProfundizacion"
    "facade"            = "com.fabricaviva.pruebas.PruebaFacade"
    "adapter"           = "com.fabricaviva.pruebas.PruebaAdapter"
    "bridge"            = "com.fabricaviva.pruebas.PruebaBridge"
    "flyweight"         = "com.fabricaviva.pruebas.PruebaFlyweight"
    "proxy"             = "com.fabricaviva.pruebas.PruebaProxy"
}

if ($patron -eq "list") {
    Write-Host "`nPatrones disponibles:" -ForegroundColor Cyan
    foreach ($k in ($PATRONES.Keys | Sort-Object)) {
        Write-Host "  .\run.ps1 $k" -ForegroundColor Yellow
    }
    Write-Host ""
    exit 0
}

$clase = $PATRONES[$patron.ToLower()]
if (-not $clase) {
    Write-Host "Patron '$patron' no reconocido. Usa: .\run.ps1 list" -ForegroundColor Red
    exit 1
}

# Compilar
Write-Host "`nCompilando..." -ForegroundColor Cyan
javac --release 17 -encoding UTF-8 -cp $CP -d target/classes $SRC
if ($LASTEXITCODE -ne 0) { Write-Host "Error de compilacion" -ForegroundColor Red; exit 1 }

# Ejecutar
Write-Host "Ejecutando: $clase`n" -ForegroundColor Green
Write-Host ("=" * 60) -ForegroundColor DarkGray
java -cp $CP $clase
Write-Host ("=" * 60) -ForegroundColor DarkGray
