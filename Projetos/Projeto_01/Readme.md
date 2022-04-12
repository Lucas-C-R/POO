### Programação Orientada a Objetos - POO29004

#### Engenharia de Telecomunicações

##### Instituto Federal de Santa Catarina



# Gerador de calendários iCalendar

> **Atenção:** esse projeto contempla somente uma pequena parte da especificação [RFC 5545](https://tools.ietf.org/html/rfc5545). Respeite a descrição apresentada aqui e não tente implementar tudo o que está descrito na [RFC 5545](https://tools.ietf.org/html/rfc5545).

## Especificação iCalendar

A especificação *Internet Calendaring and Scheduling Core Object Specification (iCalendar)*  [RFC 5545](https://tools.ietf.org/html/rfc5545) define um tipo de dados que permite o armazenamento e compartilhamento de eventos de calendário. Arquivos [iCalendar](https://en.wikipedia.org/wiki/ICalendar) geralmente possuem a extensão `.ics`, `.ical` ou `.icalendar` e  podem ser importados por aplicações como o Google Agenda, Apple Calendar, etc.

Na listagem abaixo é apresentado um exemplo de um *objeto iCalendar* (conteúdo de um arquivo texto com extensão `.ics`) com detalhes sobre a *Aula inicial de Programação Orientada a Objetos*. 

```
BEGIN:VCALENDAR
VERSION:2.0
PRODID:-//IFSC//POO29004//PT
BEGIN:VEVENT
SUMMARY:Aula inicial de Programação Orientada a Objetos
UID:C94F455B-F97B-4186-926D-63E7FBBCA5F6
DTSTAMP:20190304T133000
DTSTART:20190304T133000
DTEND:20190506T152000
END:VEVENT
END:VCALENDAR
```
Como pode ser observado na listagem acima, o principal elemento de um objeto iCalendar é o `VCALENDAR` e suas principais propriedades são:

-   `VERSION` - Informação obrigatória que indica a versão da especificação iCalendar. Atualmente o valor obrigatório para essa propriedade é `2.0`.
-   `PRODID` - Identificação do aplicativo que criou o objeto iCalendar. Apesar de não haver na especificação uma regra rígida com relação ao seu conteúdo, uma boa prática é apresentada abaixo:
    -   `PRODID:-//Nome da empresa//Nome do aplicativo//CódigoDoIdioma`

O elemento `VCALENDAR` contém um ou mais elementos `VEVENT`, sendo esse último responsável por representar um evento. Na listagem abaixo é apresentado um exemplo de um objeto iCalendar que contém três elementos `VEVENT`.

```
BEGIN:VCALENDAR
VERSION:2.0
PRODID:-//IFSC//POO29004//PT
BEGIN:VEVENT
...
END:VEVENT
BEGIN:VEVENT
...
END:VEVENT
BEGIN:VEVENT
...
END:VEVENT
END:VCALENDAR
```

As principais propriedades de um `VEVENT` são:

-   `SUMMARY` - O assunto do evento
-   `UID` - Um identificador único do evento. Não podem existir dois ou mais eventos com um mesmo identificador.
-   `DTSTAMP` - Indica a data e hora que a instância do iCalendar foi criada. Essa informação deve ser representada no formato: `AAAAMMDDTHHMMSS`. Ou seja, para a instância criada às 13:30:00 do dia 04/03/2019 o valor será `20190304T133000`.
-   `DTSTART` - Indica a data e hora que o evento começa. O formato para representação é igual ao da propriedade `DTSTAMP`.
-   `DTEND` - Indica a data e hora que o evento termina. O formato para representação é igual ao da propriedade `DTSTAMP`.
-   `LOCATION` - Um texto livre para indicar o local do evento. Por exemplo, *Laboratório de Sistemas Digitais*

Um `VEVENT` pode ter ainda outras propriedades opcionais:

-   `RRULE:` - Especifica uma regra ou padrão de repetição para eventos recorrentes. Por exemplo, a Aula de POO acontece toda segunda-feira desde o início do semestre até o dia 02/07/2019. O conjunto de recorrência é gerado considerando a data inicial especificada no `DTSTART` e excluindo as datas especificadas com a propriedade `EXDATE` (explicado mais abaixo). Isto é, a propriedade `DSTART` define a primeira instância do conjunto de recorrências.
    -   `RRULE:FREQ=WEEKLY;INTERVAL=1;UNTIL=20190702T152000;BYDAY=MO`
    -   Os valores permitidos para cada propriedade de `RRULE` são:
        -   `FREQ` - DAILY, WEEKLY, MONTLHY, YEARLY
        -   `INTERVAL` - Indica o intervalo de repetição. Por exemplo, `FREQ=WEEKLY;INTERVAL=2` indica que o evento repete a cada 2 semanas. `FREQ=DAILY;INTERVAL=1` indica que o evento repete a cada dia.
        -   `UNTIL` - Data e hora no mesmo formato da propriedade `DTEND` que especifica o término da repetição.
        -   `BYDAY` - `SU, MO, TU, WE, TH, FR, SA` . Pode ser apenas um desses valores ou pode-se combinar um ou mais, separando-os por vírgula. 
            -   Ex: `BYDAY=MO,TU` indica que o evento repete na segunda-feira e terça-feira.
-   `EXDATE` - Cada entrada especifica uma data e hora que deverá ser excluída de eventos recorrentes. Cada evento, que possua uma propriedade `RRULE`, poderá ter zero ou mais propriedades `EXDATE`. Por exemplo, a aula de POO acontece semalmente na segunda-feira, porém não haverá aula nos dias 04/03/2019 e 18/03/2019.

```bash
RRULE:FREQ=WEEKLY;INTERVAL=1;UNTIL=20190702T152000;BYDAY=MO
EXDATE:20190304T133000
EXDATE:20190318T133000
```

## Projeto a ser desenvolvido

Desenvolva uma aplicação em Java que permita ao usuário criar um objeto iCalendar, conforme descrição apresentada nas seções anteriores, e exportá-lo em arquivo. A aplicação ainda deverá permitir ao usuário alterar detalhes sobre eventos, bem como excluir eventos. Para o desenvolvimento do projeto você deve atender os seguintes requisitos:

1. Fazer um diagrama de classes UML com a modelagem de classes, que deve obrigatoriamente fazer uso de associações entre classes. O diagrama deverá ser salvo na raiz do repositório com o nome `diagrama-classe.png`.
2. Fazer uso da sobreescrita do método `toString()` da classe `Object`.
3. Criar um projeto Java com gradle e fazer uso correto do `.gitignore` neste repositório.
4. Desenvolver testes de unidade. É necessário apresentar pelo menos dois testes com valores válidos para construções de um objeto iCalendar e pelo menos dois testes com valores inválidos, para garantir que não será possível construir um objeto iCalendar incorreto.
5. Respeitar o encapsulamento de dados, responsabilidade única e divisão de responsabilidades.
6. Não ter constantes literais espalhados pelo código.
7. Garanta que seja possível executar seu projeto após o mesmo ser clonado via `git clone`. 


## Material de apoio

### Classe java.time.LocalDateTime

Use essa classe para gerar valores para as propriedades `DTSTAMP`, `DTSTART` e `DTEND`

```java
LocalDateTime localDateTime = LocalDateTime.of(2019, 04, 22, 13, 30, 00);
String dataFormatada = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
System.out.printf(dataFormatada); // Será impresso: 20190422T133000

// para obter a data e hora no momento que essa linha foi executada
LocalDateTime agora = LocalDateTime.now(); 
```



### Classe java.util.UUID

Use essa classe para gerar um identificador único universal (UUID) que pode ser usado como valor para a propriedade `UID`.

``` java
// Será impresso algo como: 48021fb2-78ca-4bf6-aa67-f96aa6f39413
System.out.printf(UUID.randomUUID().toString()); 
```



### Exemplos de calendários



#### Aulas de POO de 2019.01

Aulas de POO acontecem semalmente na segunda-feira das 13:30 às 15:20 e na terça-feira das 15:40 às 17:30. Em 2019.01 não haverá aula nos dias: 04/03/2019, 05/03/2019, 18/03/2019, 19/03/2019, 06/05/2019 e 07/05/2019.

```
BEGIN:VCALENDAR
VERSION:2.0
PRODID:-//Plano de ensino//Emerson Mello//PT
BEGIN:VEVENT
SUMMARY:Aula de Programação Orientada a Objetos
DTSTAMP:20190211T133000
DTSTART:20190211T133000
DTEND:20190211T152000
UID:481a268f-b33b-4a14-b086-5446af2bfcaf
RRULE:FREQ=WEEKLY;INTERVAL=1;UNTIL=20190702T152000;BYDAY=MO
EXDATE:20190304T133000
EXDATE:20190318T133000
EXDATE:20190506T133000
LOCATION:Laboratório Sistemas Digitais / Sala 10
END:VEVENT
BEGIN:VEVENT
SUMMARY:Aula de Programação Orientada a Objetos
DTSTAMP:20190212T154000
DTSTART:20190212T154000
DTEND:20190212T173000
UID:9d2609ed-7e63-46e8-a6d3-d28925c7f496
RRULE:FREQ=WEEKLY;INTERVAL=1;UNTIL=20190702T173000;BYDAY=TU
EXDATE:20190305T154000
EXDATE:20190319T154000
EXDATE:20190507T154000
LOCATION:Laboratório Sistemas Digitais / Sala 10
END:VEVENT
END:VCALENDAR
```



#### Aulas de Sistemas Distribuídos 2019.01

Aulas de STD acontecem:

-    Semalmente na quinta-feira das 13:30 às 15:20. 
-   A cada 15 dias (semana alternada) na quarta-feira das 13:30 às 15:20

Em 2019.01 não haverá aula nos dias: 18/04/2019, 01/05/2019 e 20/06/2019.

```
BEGIN:VCALENDAR
VERSION:2.0
PRODID:-//Plano de ensino//Emerson Mello//
BEGIN:VEVENT
SUMMARY:Aula de Sistemas Distribuídos
DTSTAMP:20190220T133000
DTSTART:20190220T133000
DTEND:20190220T152000
UID:2a8753f9-3f8c-4f81-8634-7ad52a95d07b
RRULE:FREQ=WEEKLY;INTERVAL=2;UNTIL=20190627T152000;BYDAY=WE
EXDATE:20190501T133000
LOCATION:Laboratório Sistemas Digitais / Sala 11
END:VEVENT
BEGIN:VEVENT
SUMMARY:Aula de Sistemas Distribuídos
DTSTAMP:20190214T133000
DTSTART:20190214T133000
DTEND:20190214T152000
UID:6dd39ab0-126f-4e31-808e-ca8baa3d9e8b
RRULE:FREQ=WEEKLY;INTERVAL=1;UNTIL=20190627T152000;BYDAY=TH
EXDATE:20190418T133000
EXDATE:20190620T133000
LOCATION:Laboratório Sistemas Digitais / Sala 11
END:VEVENT
END:VCALENDAR
```

### Validador de iCalendar

Em https://icalendar.org/validator.html tem um validador de objetos iCalendar, use-o para verificar se String resultante gerada pelo seu código é válida. Basta copiar a String, colar no formulário nessa página e clicar no botão Validate.