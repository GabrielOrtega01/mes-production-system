from docx import Document
from docx.enum.text import WD_ALIGN_PARAGRAPH
from docx.enum.table import WD_TABLE_ALIGNMENT, WD_CELL_VERTICAL_ALIGNMENT
from docx.enum.style import WD_STYLE_TYPE
from docx.shared import Cm, Pt, RGBColor
from docx.oxml import OxmlElement
from docx.oxml.ns import qn
from pathlib import Path

OUT = Path(r"C:\Users\gabri\OneDrive\Documentos\universidades\UTS\semestre 10\Patrones de software\Tercer Corte\ActividadS14\Actividad Semana 14 - Parcial3.docx")

def shade(cell, fill):
    tc_pr = cell._tc.get_or_add_tcPr(); shd = OxmlElement('w:shd'); shd.set(qn('w:fill'), fill); tc_pr.append(shd)

def border(cell, color='D9E2EF'):
    tc_pr = cell._tc.get_or_add_tcPr(); borders = OxmlElement('w:tcBorders')
    for edge in ('top','left','bottom','right'):
        e = OxmlElement(f'w:{edge}'); e.set(qn('w:val'),'single'); e.set(qn('w:sz'),'6'); e.set(qn('w:color'),color); borders.append(e)
    tc_pr.append(borders)

def set_font(run, name='Arial', size=11, bold=False, color=None):
    run.font.name=name; run._element.rPr.rFonts.set(qn('w:ascii'),name); run._element.rPr.rFonts.set(qn('w:hAnsi'),name); run.font.size=Pt(size); run.bold=bold
    if color: run.font.color.rgb=RGBColor.from_string(color)

def p(doc, text='', style=None, bold_lead=None):
    par=doc.add_paragraph(style=style); par.paragraph_format.space_after=Pt(7); par.paragraph_format.line_spacing=1.25
    if bold_lead and text.startswith(bold_lead):
        set_font(par.add_run(bold_lead), bold=True); set_font(par.add_run(text[len(bold_lead):]))
    else: set_font(par.add_run(text))
    return par

def heading(doc, text, level):
    par=doc.add_heading('', level=level); par.paragraph_format.space_before=Pt(14); par.paragraph_format.space_after=Pt(7)
    run=par.add_run(text); set_font(run, size={1:15,2:13,3:11}[level], bold=True, color='000000'); return par

def code(doc, text):
    table=doc.add_table(rows=1, cols=1); table.alignment=WD_TABLE_ALIGNMENT.CENTER
    cell=table.cell(0,0); shade(cell,'F3F6FA'); border(cell); cell.vertical_alignment=WD_CELL_VERTICAL_ALIGNMENT.CENTER
    par=cell.paragraphs[0]; par.paragraph_format.space_after=Pt(0)
    run=par.add_run(text); set_font(run, name='Consolas', size=8)
    doc.add_paragraph().paragraph_format.space_after=Pt(2)

def bullet(doc, text):
    par=doc.add_paragraph(style='List Bullet'); par.paragraph_format.space_after=Pt(3); set_font(par.add_run(text)); return par

def figure(doc, image_path, caption):
    par = doc.add_paragraph()
    par.alignment = WD_ALIGN_PARAGRAPH.CENTER
    par.paragraph_format.space_after = Pt(4)
    par.add_run().add_picture(str(image_path), width=Cm(15.5))
    caption_par = doc.add_paragraph()
    caption_par.alignment = WD_ALIGN_PARAGRAPH.CENTER
    caption_par.paragraph_format.space_after = Pt(2)
    caption_run = caption_par.add_run(caption)
    set_font(caption_run, size=9)
    caption_run.italic = True
    note = doc.add_paragraph()
    note.alignment = WD_ALIGN_PARAGRAPH.CENTER
    note.paragraph_format.space_after = Pt(0)
    set_font(note.add_run('Nota. Elaboración propia.'), size=8)

doc=Document()
sec=doc.sections[0]; sec.top_margin=Cm(2.5); sec.bottom_margin=Cm(2.5); sec.left_margin=Cm(3); sec.right_margin=Cm(3)
styles=doc.styles
styles['Normal'].font.name='Arial'; styles['Normal']._element.rPr.rFonts.set(qn('w:hAnsi'),'Arial'); styles['Normal'].font.size=Pt(11)

# Cover
for _ in range(4): doc.add_paragraph()
title=doc.add_paragraph(); title.alignment=WD_ALIGN_PARAGRAPH.CENTER; title.paragraph_format.space_after=Pt(18); set_font(title.add_run('Semana 14 - Patrones de comportamiento'),size=18,bold=True)
subtitle=doc.add_paragraph(); subtitle.alignment=WD_ALIGN_PARAGRAPH.CENTER; set_font(subtitle.add_run('Observer, State y Visitor'),size=15,bold=True)
for _ in range(8): doc.add_paragraph()
for line in ['Integrantes:', 'Gabriel Augusto Ortega Martínez', 'Jhonathan David Rojas Molina', '', 'Universitaria Tecnológica de Santander', 'Facultad de Ciencias Naturales e Ingenierías', 'Ingeniería de Sistemas - SNIES 101596', '', 'Patrones de software Grupo: A194', 'Docente: Eliecer Montero Ojeda', '', 'Bucaramanga, Noviembre de 2026']:
    q=doc.add_paragraph(); q.alignment=WD_ALIGN_PARAGRAPH.CENTER; set_font(q.add_run(line), size=11, bold=(line=='Integrantes:'))
doc.add_page_break()

heading(doc,'Contenido',1)
for item in ['Introducción','Objetivos del proyecto','Objetivo general','Objetivos específicos','Patrones de comportamiento','Patrón Observer','Patrón State','Patrón Visitor','Pruebas visuales en index.html','Architecture Decision Record','Referencias','Evidencias visuales de pruebas']:
    p(doc,item)
doc.add_page_break()

heading(doc,'Sistema de Gestión Académica',1)
heading(doc,'Introducción',1)
p(doc,'El Sistema de Gestión Académica permite registrar y gestionar solicitudes de homologación realizadas por los estudiantes. Estas solicitudes requieren un ciclo de vida controlado, deben informar sus cambios a diferentes módulos y necesitan producir reportes sin mezclar esa responsabilidad con el modelo de dominio.')
p(doc,'La solución propuesta aplica los patrones Observer, State y Visitor en un proyecto Java independiente llamado ActividadS14. El proyecto separa los observadores, los estados y las operaciones de reporte en paquetes distintos, con el fin de mantener bajo acoplamiento, facilitar la extensión y hacer explícitas las reglas del proceso académico.')
heading(doc,'Objetivos del proyecto',1)
heading(doc,'Objetivo general',2)
p(doc,'Desarrollar un módulo de gestión académica en Java que administre solicitudes de homologación, controle sus transiciones de estado, notifique cambios a los módulos interesados y permita generar información sin modificar las clases del dominio.')
heading(doc,'Objetivos específicos',2)
for x in ['Implementar Observer para notificar al historial académico, al panel del estudiante y al módulo de auditoría cuando una solicitud cambia de estado.', 'Aplicar State para validar las transiciones entre los estados radicada, en revisión, aprobada y rechazada.', 'Aplicar Visitor para generar un reporte de solicitudes y calcular estadísticas sobre la misma colección de objetos.', 'Construir pruebas automatizadas con JUnit 5 y una interfaz web local que muestre de forma visual los escenarios probados.']: bullet(doc,x)
p(doc,'Los siguientes patrones resuelven problemas complementarios del módulo. Observer desacopla la notificación de los componentes interesados; State encapsula las reglas de transición; y Visitor permite añadir operaciones sobre las solicitudes sin alterar su estructura.')

heading(doc,'Patrones de comportamiento',1)
p(doc,'Los patrones de comportamiento se ocupan de la comunicación y la asignación de responsabilidades entre objetos. En el contexto académico, permiten representar reglas de negocio que cambian con el estado de una solicitud y comunicar esos cambios sin que la solicitud dependa de módulos concretos.')
table=doc.add_table(rows=1, cols=3); table.alignment=WD_TABLE_ALIGNMENT.CENTER; table.style='Table Grid'
for c,t in zip(table.rows[0].cells,['Patrón','Problema atendido','Clase principal']): shade(c,'1F4E78'); set_font(c.paragraphs[0].add_run(t),bold=True,color='FFFFFF'); border(c); c.vertical_alignment=WD_CELL_VERTICAL_ALIGNMENT.CENTER
for row in [('Observer','Notificar varios módulos ante un cambio.','SolicitudHomologacion'),('State','Controlar las transiciones válidas.','EstadoSolicitud'),('Visitor','Agregar reportes y estadísticas.','VisitanteAcademico')]:
    cells=table.add_row().cells
    for c,t in zip(cells,row): set_font(c.paragraphs[0].add_run(t)); border(c); c.vertical_alignment=WD_CELL_VERTICAL_ALIGNMENT.CENTER

heading(doc,'Patrón Observer',2)
heading(doc,'Descripción del patrón',3)
p(doc,'Observer define una dependencia uno a muchos: cuando el sujeto cambia de estado, notifica automáticamente a todos sus observadores. En este proyecto, SolicitudHomologacion es el sujeto y los observadores son HistorialAcademicoObserver, PanelEstudianteObserver y AuditoriaObserver.')
heading(doc,'Implementación',3)
code(doc,'''public void cambiarEstado(EstadoSolicitud nuevoEstado) {
    estado = nuevoEstado;
    observadores.forEach(o -> o.actualizar(this));
}''')
p(doc,'En la Figura 1, la lista observadores almacena los interesados, suscribir() los registra y cambiarEstado() recorre la lista para invocar actualizar(this). Esa notificación única a múltiples objetos es el comportamiento solicitado para Observer.')
figure(doc, OUT.parent / 'docs' / 'capturas' / 'codigo-observer-implementacion.png', 'Figura 1. Código que registra y notifica múltiples observadores.')
p(doc,'La solicitud no necesita conocer cómo cada módulo procesa el cambio. Por ejemplo, el historial agrega una materia aprobada, el panel actualiza el estado visible y auditoría conserva el evento.')
heading(doc,'Prueba de funcionamiento',3)
p(doc,'La prueba Observer crea una solicitud, registra tres observadores y la lleva a los estados EN_REVISION y APROBADA. La verificación confirma que el historial contiene Programación I, el panel muestra APROBADA y auditoría recibe dos eventos.')

heading(doc,'Patrón State',2)
heading(doc,'Descripción del patrón',3)
p(doc,'State permite que un objeto cambie su comportamiento cuando cambia su estado interno. SolicitudHomologacion delega sus operaciones a un objeto EstadoSolicitud, por lo cual evita un switch extenso y concentra las reglas de transición en clases específicas.')
heading(doc,'Diagrama UML',3)
code(doc,'''SolicitudHomologacion --> EstadoSolicitud
RadicadaState, EnRevisionState, AprobadaState y RechazadaState
implementan EstadoSolicitud.''')
heading(doc,'Implementación',3)
code(doc,'''public final class EnRevisionState implements EstadoSolicitud {
    public void aprobar(SolicitudHomologacion s) {
        s.cambiarEstado(new AprobadaState());
    }
    public void rechazar(SolicitudHomologacion s) {
        s.cambiarEstado(new RechazadaState());
    }
}''')
p(doc,'La Figura 2 muestra la delegación hacia el objeto estado: enviarRevision(), aprobar() y rechazar() no contienen condicionales; cada operación se delega a EstadoSolicitud. De esta forma, las clases de estado determinan si la transición es válida o generan la excepción correspondiente.')
figure(doc, OUT.parent / 'docs' / 'capturas' / 'codigo-state-implementacion.png', 'Figura 2. Código que delega las transiciones al patrón State.')
heading(doc,'Prueba de funcionamiento',3)
p(doc,'La prueba confirma que no se puede aprobar una solicitud en estado RADICADA. Después de enviarla a revisión, puede aprobarse. Al estar APROBADA, un intento de rechazarla genera IllegalStateException.')

heading(doc,'Patrón Visitor',2)
heading(doc,'Descripción del patrón',3)
p(doc,'Visitor representa una operación que se realiza sobre los elementos de una estructura. La interfaz VisitanteAcademico declara visitar(SolicitudHomologacion). Las operaciones concretas son ReporteSolicitudesVisitor y EstadisticasVisitor.')
heading(doc,'Implementación',3)
code(doc,'''public void aceptar(VisitanteAcademico visitante) {
    visitante.visitar(this);
}''')
p(doc,'En la Figura 3, SolicitudHomologacion implementa ElementoAcademico y su método aceptar() delega la operación al VisitanteAcademico. Así, los visitantes de reporte, CSV y estadísticas pueden trabajar sobre la misma solicitud sin agregar métodos de reporte al modelo.')
figure(doc, OUT.parent / 'docs' / 'capturas' / 'codigo-visitor-implementacion.png', 'Figura 3. Código que recibe y delega la operación al Visitor.')
p(doc,'El reporte obtiene una línea por solicitud, el exportador CSV genera encabezado y filas interoperables, y las estadísticas cuentan el total y las aprobaciones. Las tres operaciones recorren las mismas solicitudes, sin añadir métodos de generación de reportes a SolicitudHomologacion.')
heading(doc,'Prueba de funcionamiento',3)
p(doc,'Con una solicitud aprobada y una rechazada, el visitante de reporte genera dos líneas y el visitante CSV genera un encabezado con dos filas de datos. El visitante de estadísticas calcula dos solicitudes, una aprobación y una tasa de aprobación de 50 %.')

heading(doc,'Pruebas visuales en index.html',1)
p(doc,'El proyecto incluye un panel visual local en index.html. La página presenta tres tarjetas, una por patrón, y cada botón muestra resultados PASS para el escenario correspondiente. Este recurso facilita exponer los casos durante la sustentación, mientras las pruebas JUnit 5 permanecen como verificación automatizada del código Java.')
code(doc,'''mvn test
# Resultado esperado: Tests run: 3, Failures: 0, Errors: 0, Skipped: 0''')
heading(doc,'Architecture Decision Record',1)
p(doc,'El archivo docs/adr/ADR-001-patrones-comportamiento.md documenta las decisiones de arquitectura. Observer se seleccionó frente a llamadas directas para permitir nuevos interesados sin cambiar la solicitud. State reemplaza un switch y hace fallar explícitamente las transiciones inválidas. Visitor separa los reportes y estadísticas del modelo de solicitudes.')
heading(doc,'Referencias',1)
for ref in ['Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). Design patterns: Elements of reusable object-oriented software. Addison-Wesley.', 'Freeman, E., & Robson, E. (2020). Head First Design Patterns (2nd ed.). O’Reilly Media.', 'Refactoring.Guru. (2023). Observer Pattern. https://refactoring.guru/design-patterns/observer', 'Refactoring.Guru. (2023). State Pattern. https://refactoring.guru/design-patterns/state', 'Refactoring.Guru. (2023). Visitor Pattern. https://refactoring.guru/design-patterns/visitor']:
    p(doc,ref)

CAPTURES = OUT.parent / 'docs' / 'capturas'
doc.add_page_break()
heading(doc, 'Evidencias visuales de pruebas', 1)
p(doc, 'A continuación se presentan los pantallazos de la interfaz local index.html. Cada evidencia corresponde a uno de los escenarios de prueba implementados para los patrones de comportamiento.')
figure(doc, CAPTURES / 'prueba-observer.png', 'Figura 4. Resultado visual de la prueba del patrón Observer.')
doc.add_page_break()
figure(doc, CAPTURES / 'prueba-state.png', 'Figura 5. Resultado visual de la prueba del patrón State.')
doc.add_page_break()
figure(doc, CAPTURES / 'prueba-visitor.png', 'Figura 6. Resultado visual de la prueba del patrón Visitor.')

OUT.parent.mkdir(parents=True, exist_ok=True)
doc.save(OUT)
print(OUT)
