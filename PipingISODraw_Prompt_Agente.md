**PIPING ISO DRAW**

Prompt Técnico Completo para Agente IA

*Aplicativo Android de Desenho Isométrico para Tubulação Industrial*

**Versão:** 1.0  •  **Normas:** ASME B16.x  /  ISO 10628  •  **Plataforma:** Android (Kotlin \+ Jetpack Compose)

# **0\. Como Usar Este Prompt**

Este documento é um prompt estruturado destinado a um Agente IA de desenvolvimento de software (ex: Claude Code, Cursor, Devin). O agente deve ler cada seção em ordem e executar as tarefas descritas de forma autônoma, tomando decisões técnicas baseadas nas especificações aqui fornecidas.

| Instruções para o Agente |
| :---- |
| 1\. Leia este documento completo antes de escrever qualquer código. |
| 2\. Siga a arquitetura definida na Seção 3 sem desvios não justificados. |
| 3\. Implemente em fases conforme o roadmap da Seção 8\. |
| 4\. Para cada componente de norma (ASME/ISO), consulte as tabelas da Seção 5\. |
| 5\. Escreva testes unitários para toda lógica de cálculo de engenharia. |
| 6\. Documente decisões arquiteturais não óbvias com comentários no código. |
| 7\. Ao finalizar cada fase, gere um relatório de progresso com itens concluídos e pendências. |

# **1\. Visão Geral do Produto**

Desenvolver um aplicativo Android nativo chamado Piping ISO Draw, equivalente funcional ao Easy Isometric, voltado para profissionais de tubulação industrial nas áreas de Oil & Gas, petroquímica, mineração e construção industrial.

## **1.1 Objetivo**

Permitir que engenheiros, projetistas e técnicos de tubulação criem, editem e exportem isométricos de tubulação diretamente no campo, sem necessidade de acesso a estações de trabalho com AutoCAD ou software CAD dedicado.

## **1.2 Referências de mercado**

| Produto | Posicionamento |
| :---- | :---- |
| Easy Isometric | Referência principal — funcionalidade alvo |
| Aisome (zacherl.dev) | Referência de UX e simplicidade de interface |
| AutoCAD Plant 3D | Referência de completude de norma (desktop) |
| SmartSketch | Referência de exportação DXF |

## **1.3 Diferenciais obrigatórios**

* Suporte nativo a ASME B16.x e ISO 10628/15649 no mesmo app

* Cálculo automático de take-out, centerline e end-to-end dimensions

* BOM (Bill of Materials) gerada em tempo real conforme o usuário desenha

* Exportação PDF com title block configurável e DXF para CAD

* Funciona 100% offline — dados de norma embutidos no app

* Interface touch-first otimizada para tablets e smartphones

# **2\. Normas Técnicas Suportadas**

## **2.1 ASME (American Society of Mechanical Engineers)**

| Norma | Escopo | Uso no App |
| :---- | :---- | :---- |
| ASME B36.10M | Tubos de aço soldados e sem costura | Tabela de OD e espessura por NPS/Schedule |
| ASME B36.19M | Tubos de aço inoxidável | Idem — schedules 5S, 10S, 40S, 80S |
| ASME B16.9 | Fittings butt-weld — curvas, tês, reduções | Take-out e dimensões de fabricação |
| ASME B16.11 | Fittings socket-weld e roscados | Dimensões por NPS |
| ASME B16.5 | Flanges — classes 150 a 2500 | Dimensões face-a-face e bolt circles |
| ASME B16.25 | Preparação de extremidades (bevel) | End prep automático por espessura |
| ASME B31.3 | Process Piping — código de projeto | Referência no title block |
| ASME Y14.5 | Tolerâncias dimensionais | Tolerâncias padrão no desenho |

## **2.2 ISO / EN (International / European Standards)**

| Norma | Escopo | Uso no App |
| :---- | :---- | :---- |
| ISO 10628-2 | Símbolos para diagramas de processo | Biblioteca de símbolos ISO |
| ISO 15649 | Tubulação industrial — requisitos gerais | Referência normativa |
| EN 10253-2 | Fittings butt-weld — dimensões (aço carbono) | Tabela de take-out métrico |
| EN 10253-4 | Fittings butt-weld — aço inoxidável | Idem — série austenítica |
| EN 1092-1 | Flanges — PN 6 a PN 400 | Dimensões face-a-face por PN |
| ISO 1127 | Tubos de aço inoxidável — dimensões | OD e espessura série L/M/H |
| ISO 2768 | Tolerâncias gerais | Tolerâncias padrão no desenho |
| EN 13480 | Tubulação industrial metálica | Referência no title block |

## **2.3 Configuração de norma por projeto**

Cada projeto no app deve ter uma configuração de norma definida no momento da criação:

enum class PipingStandard { ASME, ISO, MIXED }

enum class UnitSystem { IMPERIAL, METRIC }

Quando MIXED: simbologia ISO \+ dimensões ASME convertidas para mm. O app deve converter NPS ↔ DN automaticamente via tabela fixa embutida.

| NPS (ASME) | DN (ISO) |
| :---- | :---- |
| 1/2" | DN 15 |
| 3/4" | DN 20 |
| 1" | DN 25 |
| 1-1/2" | DN 40 |
| 2" | DN 50 |
| 3" | DN 80 |
| 4" | DN 100 |
| 6" | DN 150 |
| 8" | DN 200 |
| 10" | DN 250 |
| 12" | DN 300 |
| 16" | DN 400 |
| 20" | DN 500 |
| 24" | DN 600 |

# **3\. Arquitetura Técnica**

## **3.1 Stack tecnológico**

| Camada | Tecnologia |
| :---- | :---- |
| Linguagem | Kotlin 1.9+ |
| UI Framework | Jetpack Compose \+ Canvas API |
| Rendering Engine | Android Canvas (SurfaceView para performance) |
| Banco de dados local | Room (SQLite) — projetos, componentes, normas |
| DI (Injeção de dependência) | Hilt |
| Arquitetura | MVVM \+ Repository Pattern \+ Clean Architecture |
| Exportação PDF | PdfDocument (nativo Android) — fallback: iText7 |
| Exportação DXF | Implementação manual ASCII DXF R12/R2000 |
| Exportação PNG/SVG | Canvas.toBitmap() \+ SVG builder custom |
| Testes | JUnit 5 \+ MockK \+ Compose Testing |
| CI/CD | GitHub Actions — lint \+ test \+ build APK |

## **3.2 Módulos do projeto**

O projeto deve ser organizado em módulos Gradle separados:

* :app — módulo principal, Activity, navegação

* :core:domain — entidades, casos de uso, interfaces de repositório

* :core:data — Room, repositórios, datasources

* :core:standards — biblioteca de normas ASME e ISO (dados embutidos)

* :feature:drawing — engine de desenho isométrico

* :feature:components — catálogo e seletor de componentes

* :feature:bom — geração de lista de materiais

* :feature:export — PDF, DXF, PNG, CSV

* :feature:settings — configurações de projeto e usuário

## **3.3 Entidades de domínio principais**

data class Project(

    val id: UUID,

    val name: String,

    val standard: PipingStandard,

    val units: UnitSystem,

    val lineNumber: String,

    val fluidService: String,

    val components: List\<PlacedComponent\>

)

data class PlacedComponent(

    val id: UUID,

    val type: ComponentType,

    val position: IsoPoint,   // (gx, gy, gz) no grid isométrico

    val orientation: Orientation,

    val spec: PipeSpec,        // dn/nps, schedule, material, rating

    val tag: String?           // tag de instrumento/equipamento

)

data class IsoPoint(val gx: Int, val gy: Int, val gz: Int)

data class PipeSpec(val dn: Int, val schedule: String, val material: String, val rating: String)

# **4\. Engine de Desenho Isométrico**

## **4.1 Projeção isométrica**

Toda a renderização usa projeção isométrica padrão a 30°/60°. O canvas isométrico mapeia coordenadas de grid (gx, gy, gz) para pixels de tela:

fun isoToScreen(gx: Int, gy: Int, gz: Int, cellSize: Float, origin: PointF): PointF {

    val sx \= (gx \- gy) \* cellSize \* cos(PI/6).toFloat()

    val sy \= (gx \+ gy) \* cellSize \* sin(PI/6).toFloat() \- gz \* cellSize

    return PointF(origin.x \+ sx, origin.y \+ sy)

}

## **4.2 Grid e navegação**

* Grid isométrico renderizado a 30°/60° com linhas dos 3 eixos (X, Y, Z)

* Zoom: pinch-to-zoom de 0.3x a 5.0x com inércia suave

* Pan: drag com um dedo — momento e limites de canvas

* Snap automático para o nó de grid mais próximo ao tocar

* Destaque de eixos: X (vermelho), Y (verde), Z (azul) — padrão CAD

* Mini-mapa de navegação no canto inferior direito para projetos grandes

## **4.3 Sistema de conexão entre componentes**

Cada componente tem portas de conexão (connection ports) com posição e direção. O sistema de snap deve:

* Detectar a porta de conexão mais próxima ao soltar um componente

* Encaixar automaticamente quando a distância for menor que 1 célula de grid

* Validar compatibilidade de DN/schedule entre componentes conectados

* Exibir indicador visual de alerta (amarelo) quando DNs diferentes se conectam sem redução

* Bloquear conexão (vermelho) quando a conexão seria fisicamente impossível

## **4.4 Interações de edição**

* Tap: selecionar componente (exibe painel de propriedades)

* Long press: menu contextual (editar, duplicar, rotacionar, deletar)

* Drag: mover componente selecionado — com snap de grid

* Dois dedos na área vazia: pan do canvas

* Pinch na área vazia: zoom

* Undo/Redo: Command pattern com histórico de 50 ações

* Seleção múltipla: lasso ou tap+shift

# **5\. Biblioteca de Componentes**

## **5.1 Componentes obrigatórios — Fase 1**

| Componente | Portas | Símbolo |
| :---- | :---- | :---- |
| Tubo reto (pipe run) | 2 (início/fim) | Linha contínua — espessura proporcional ao DN |
| Cotovelo 90° (elbow 90°) | 2 | L com arco de curvatura — LR e SR |
| Cotovelo 45° (elbow 45°) | 2 | Segmento diagonal |
| Tê igual (equal tee) | 3 | T — ramal perpendicular |
| Tê reduzido (reducing tee) | 3 | T — ramal com redução visual |
| Redução concêntrica | 2 | Cone simétrico |
| Redução excêntrica | 2 | Cone assimétrico (flat bottom/top) |
| Tampa (cap) | 1 | Semicírculo ou traço duplo |
| Flange WN (weld neck) | 2 | Dois traços paralelos com espessura |
| Flange SO (slip-on) | 2 | Dois traços próximos |
| Flange cega (blind) | 1 | Traço único espesso |
| Válvula gaveta (gate) | 2 | Dois triângulos opostos — vértice-a-vértice |
| Válvula globo (globe) | 2 | Círculo com haste vertical |
| Válvula borboleta (butterfly) | 2 | Losango com linha central |
| Válvula de retenção (check) | 2 | Triângulo \+ traço de retenção |
| Válvula esfera (ball) | 2 | Círculo sólido com haste |

## **5.2 Componentes — Fase 2**

* Válvula agulha (needle), válvula diafragma, válvula angular

* Junta de expansão (expansion joint), compensador de dilatação

* Filtro tipo Y (Y-strainer), filtro tipo cesta (basket strainer)

* Trap de vapor (steam trap)

* Instrumento de pressão (PT, PI), temperatura (TT, TI), fluxo (FT, FI)

* Orifice plate, venturi, rotâmetro

* Suporte de tubulação (pipe support, hanger, guide, anchor)

* Bocal de equipamento (equipment nozzle)

## **5.3 Diferenças visuais ASME vs ISO**

| Componente | ASME | ISO |
| :---- | :---- | :---- |
| Válvula gaveta | Triângulos com vértices tocando | Triângulos com barra vertical no centro |
| Redução excêntrica | Cone assimétrico simples | Cone com indicação de posicionamento (FBO/FBU) |
| Flange cega | Traço duplo espesso | Traço simples com hachura |
| Instrumento em campo | Balão com borda simples | Balão com borda dupla |
| Válvula borboleta | Elipse com linha | Losango com linha central |
| Junta flangeada | Dois traços paralelos | Dois traços com círculos de parafuso |

# **6\. Cálculos de Engenharia Automáticos**

## **6.1 Take-out de fittings**

O take-out é a distância do centro do fitting até a face de conexão, usada para calcular o comprimento real de tubulação necessário. O app deve calcular automaticamente para cada fitting inserido.

Exemplos de take-out para cotovelo 90° LR (ASME B16.9) — valores em mm:

| NPS / DN | Take-out 90° LR (A) |
| :---- | :---- |
| 1" / DN 25 | 38 mm |
| 1-1/2" / DN 40 | 57 mm |
| 2" / DN 50 | 76 mm |
| 3" / DN 80 | 114 mm |
| 4" / DN 100 | 152 mm |
| 6" / DN 150 | 229 mm |
| 8" / DN 200 | 305 mm |
| 10" / DN 250 | 381 mm |
| 12" / DN 300 | 457 mm |

## **6.2 Comprimento de tubo por trecho**

Para cada trecho de tubo entre dois componentes, o app calcula automaticamente:

Comprimento de tubo \= Distância centerline \- Take-out(comp\_A) \- Take-out(comp\_B)

Este valor é exibido como cota no desenho e usado na BOM para quantificação.

## **6.3 Dimensões de centerline**

O app deve exibir automaticamente as dimensões de centerline em todos os trechos quando o modo de cotagem está ativo. Estas dimensões seguem o padrão do isométrico:

* Dimensões horizontais: medidas ao longo dos eixos isométricos X e Y

* Dimensões verticais: medidas ao longo do eixo Z (elevação)

* Cotas de elevação: mostradas como EL. \+XX.XXX m a partir do datum do projeto

* North arrow: indicação de norte do projeto (configurável por projeto)

# **7\. Lista de Materiais (BOM)**

## **7.1 Geração automática**

A BOM deve ser atualizada em tempo real conforme o usuário adiciona, remove ou modifica componentes. Cada linha da BOM contém:

| Campo | Exemplo | Fonte |
| :---- | :---- | :---- |
| Item | 1, 2, 3... | Sequencial automático |
| Quantidade | 3 Pcs | Contagem de componentes iguais |
| Descrição | ELBOW 90° LR BW | Descritivo normalizado automático |
| Tamanho | 4" SCH 40 / DN 100 | Da spec do componente |
| Material | A234 WPB / 1.0460 | Selecionado pelo usuário |
| Norma de referência | ASME B16.9 | Definida pela norma do projeto |
| Peso unit. (kg) | 2.35 | Tabela de pesos embutida |
| Peso total (kg) | 7.05 | Calculado automaticamente |

## **7.2 Exportação da BOM**

* CSV: separado por ponto e vírgula, compatível com Excel

* XLSX: planilha formatada com cabeçalho e totais

* PDF: tabela integrada ao isométrico ou standalone

* JSON: para integração com sistemas ERP/MRP

# **8\. Exportação de Desenhos**

## **8.1 Exportação PDF**

O PDF deve incluir:

* Desenho isométrico na orientação landscape (A3 ou A1)

* Title block configurável no canto inferior direito

* BOM no canto inferior esquerdo ou página separada

* Norte, escala (NTS — Not To Scale), revisão e data

* Notas gerais e notas de soldagem

Campos do title block configuráveis: Projeto, Linha, Fluido, Pressão de Projeto, Temperatura de Projeto, Classe de Tubulação, Revisão, Data, Desenhado por, Verificado por, Aprovado por, Número do Documento.

## **8.2 Exportação DXF**

Formato DXF ASCII compatível com AutoCAD R2000 e superiores. Camadas (layers) obrigatórias:

* ISO-PIPE: tubos retos

* ISO-FITTINGS: curvas, tês, reduções

* ISO-VALVES: válvulas

* ISO-INSTRUMENTS: instrumentos e conexões de instrumentação

* ISO-DIMENSIONS: cotas e textos de dimensão

* ISO-BOM: tabela de materiais

* ISO-TITLEBLOCK: cabeçalho do desenho

## **8.3 Exportação PNG/SVG**

* PNG: resolução configurável (150, 300 ou 600 DPI)

* SVG: vetor escalável, útil para integração web e relatórios

# **9\. Interface e UX**

## **9.1 Layout da tela principal**

A tela de desenho deve ter layout adaptável para tablet (landscape preferencial) e smartphone:

* Painel de componentes: lateral esquerda (tablet) ou bottom sheet (smartphone)

* Canvas de desenho: área central, ocupa 70% da tela

* Painel de propriedades: lateral direita ao selecionar componente

* Toolbar superior: undo, redo, zoom fit, grid toggle, norte, exportar

* Barra de status inferior: DN atual, norma ativa, número de componentes

## **9.2 Fluxo de trabalho**

* Novo projeto: wizard com nome, norma (ASME/ISO), unidades, dados de linha

* Selecionar componente → tocar no canvas → componente colocado com snap

* Tocar componente existente → painel de propriedades (DN, schedule, material, tag)

* Long press → menu: rotacionar (4 orientações), espelhar, duplicar, deletar

* Arrastar componente → reposicionamento com snap e validação de conexões

* Botão BOM: visualizar lista de materiais em tempo real

* Botão Exportar: escolher formato e compartilhar

## **9.3 Temas e acessibilidade**

* Dark mode e Light mode (segue configuração do sistema)

* Tamanho de texto acessível (suporte a Dynamic Type)

* Cores de status: verde (conectado), amarelo (alerta de spec), vermelho (erro)

* Haptic feedback ao encaixar componentes

# **10\. Roadmap de Implementação**

## **Fase 1 — MVP (estimativa: 10–14 semanas)**

* Setup do projeto Android com arquitetura MVVM \+ módulos Gradle

* Engine de rendering isométrico com Canvas API

* Grid isométrico com zoom, pan e snap

* Biblioteca de 16 componentes básicos (Seção 5.1) — simbologia ASME

* Sistema de conexão e validação entre componentes

* Banco de dados Room com entidades Project e PlacedComponent

* Painel de propriedades (DN, schedule, material)

* Geração de BOM básica em tempo real

* Exportação PNG e PDF simples

* Testes unitários para engine de projeção isométrica e cálculo de take-out

## **Fase 2 — Profissional (estimativa: 8–10 semanas)**

* Tabelas completas ASME B16.9 / B16.5 embutidas no app

* Cálculo automático de take-out e comprimento de tubo

* Cotagem automática (centerline dimensions, elevações)

* Simbologia ISO — modo de norma por projeto

* Conversor NPS ↔ DN automático

* BOM com pesos, normas de referência e descritivos normalizados

* Exportação DXF com layers separadas

* Title block configurável no PDF

* Undo/Redo com histórico de 50 ações

## **Fase 3 — Avançado (estimativa: 8–10 semanas)**

* Biblioteca completa de Fase 2 (instrumentos, suportes, strainers, traps)

* Templates de linha por classe de tubulação (piping class)

* Importação/exportação de projetos em JSON para backup e compartilhamento

* Múltiplos isométricos por projeto (line list)

* Exportação XLSX da BOM integrada com índice de materiais

* Integração com galeria de fotos (anexar fotos de campo ao isométrico)

* Widget de notas de soldagem (weld map)

* Revisão de desenho (rev A, B, C) com controle de alterações

# **11\. Requisitos de Teste**

## **11.1 Testes obrigatórios**

* Projeção isométrica: verificar isoToScreen e screenToIso para 50+ pontos de grid

* Take-out ASME: comparar saída do cálculo com tabela B16.9 para todos os DNs suportados

* Conexão de componentes: testar snap, validação de spec e rejeição de conexões inválidas

* BOM: verificar contagem, descrição e peso para projetos de referência

* Exportação PDF: verificar geração de arquivo e presença de campos obrigatórios

* Conversão NPS/DN: verificar tabela completa sem erros de arredondamento

## **11.2 Cobertura mínima**

* Lógica de domínio (cálculos de engenharia): 90% de cobertura

* Repositórios e datasources: 80% de cobertura

* ViewModels: 75% de cobertura

* UI (Compose): testes de smoke para fluxos principais

# **12\. Entregáveis Esperados do Agente**

Ao final de cada fase, o agente deve entregar:

* Código-fonte completo no repositório Git com histórico de commits organizados

* APK de release assinado (debug para fase 1\)

* Relatório de progresso listando itens implementados e pendências

* Resultado de todos os testes automatizados (JUnit report)

* README atualizado com instruções de build e arquitetura

* Lista de decisões técnicas tomadas com justificativa

*Piping ISO Draw — Prompt Técnico v1.0  •  Gerado com assistência de Claude (Anthropic)*