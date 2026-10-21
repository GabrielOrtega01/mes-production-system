param([string]$filtro = "")

$JAR  = "lib\junit-platform-console-standalone-1.10.3.jar"
$SRC_MAIN = Get-ChildItem "src\main\java" -Recurse -Filter "*.java" | Select-Object -ExpandProperty FullName
$SRC_TEST = Get-ChildItem "src\test\java"  -Recurse -Filter "*.java" | Select-Object -ExpandProperty FullName
$CP   = "target\classes;target\test-classes;$JAR"

Write-Host "`n══════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "  FabricaViva MES — Test Runner JUnit 5" -ForegroundColor Cyan
Write-Host "══════════════════════════════════════════`n" -ForegroundColor Cyan

# Crear directorios de salida
New-Item -ItemType Directory -Force -Path "target\classes"      | Out-Null
New-Item -ItemType Directory -Force -Path "target\test-classes" | Out-Null

# 1. Compilar fuentes principales
Write-Host "[1/3] Compilando fuentes principales..." -ForegroundColor Yellow
$tmpMain = [System.IO.Path]::GetTempFileName() + ".txt"
($SRC_MAIN | ForEach-Object { "`"" + $_.Replace('\','/') + "`"" }) |
    Set-Content $tmpMain -Encoding ASCII
javac --release 17 -encoding UTF-8 -cp $JAR -d target\classes "@$tmpMain"
if ($LASTEXITCODE -ne 0) { Write-Host "ERROR compilando fuentes principales." -ForegroundColor Red; exit 1 }

# 2. Compilar tests
Write-Host "[2/3] Compilando tests..." -ForegroundColor Yellow
$tmpTest = [System.IO.Path]::GetTempFileName() + ".txt"
($SRC_TEST | ForEach-Object { "`"" + $_.Replace('\','/') + "`"" }) |
    Set-Content $tmpTest -Encoding ASCII
javac --release 17 -encoding UTF-8 -cp $CP -d target\test-classes "@$tmpTest"
if ($LASTEXITCODE -ne 0) { Write-Host "ERROR compilando tests." -ForegroundColor Red; exit 1 }

# 3. Ejecutar tests
Write-Host "[3/3] Ejecutando tests...`n" -ForegroundColor Yellow

$args = @(
    "-jar", $JAR,
    "--classpath", "target\classes;target\test-classes",
    "--scan-classpath", "target\test-classes",
    "--details", "tree",
    "--disable-banner"
)
if ($filtro -ne "") {
    $args += @("--select-class", "com.fabricaviva.$filtro")
}

java @args

Write-Host "`n══════════════════════════════════════════`n" -ForegroundColor Cyan
