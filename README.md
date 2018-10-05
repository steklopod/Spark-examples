# Проект для изучения Apache Spark

>JDK 1.8, Scala 2.11.12, sbt 1.2.3 and Spark 2.3.2

Этот проект содержит фрагменты кода Scala для иллюстрации различных концепций Apache Spark. Он поможет вам начать 
изучение Apache Spark (как программист _Scala_).

## Примеры

Примеры можно найти в разделе `src/main/scala`. Лучший способ использовать их - начать с чтения кода и его комментариев. 
Затем, поскольку каждый файл содержит определение объекта основным методом, запустите его и рассмотрите вывод. 
Соответствующие сообщения в блогах и ответы `StackOverflow` перечислены в различных пакетах файлах `README.md`.

| Пакет или файл                   | Что иллюстрирует      |
|----------------------------------|-----------------------|
|        Ex1_SimpleRDD             | Как выполнить свой первый, очень простой, `Spark Job`. Смотрите также [An easy way to start learning Spark](http://www.river-of-bytes.com/2014/11/an-easy-way-to-start-learning-spark.html).
|        Ex2_Computations          | Как RDD работают в более сложных вычислениях. Смотрите также [Spark computations](http://www.river-of-bytes.com/2014/11/spark-computations.html). |
|        Ex3_CombiningRDDs         | Операции с несколькими RDD |
|        Ex4_MoreOperationsOnRDDs  | Более сложные операции на отдельных RDD |
|        Ex5_Partitions            | Явный контроль над разбиением на разделы для производительности и масштабируемости |
|        Ex6_Accumulators          | Как использовать аккумуляторы Spark для эффективного сбора результатов распределенных вычислений |
| [hiveql](src/main/scala/hiveql)  | Использование функций `HiveQL` в `HiveContext`. Подробные сведения см. В локальном `README.md` в этом каталоге |
| [special](src/main/scala/special) | Специальные/предварительные примеры `RDD` |
| [dataset](src/main/scala/dataset) | Ряд примеров `Dataset` (запрашиваемая коллекция, которая статически типизирована)  |
| [dataframe](src/main/scala/dataframe) |Ряд примеров `DataFrame` (запрашиваемая коллекция, которая динамически - и слабо типизирована)  |
| [sql](src/main/scala/sql)             | Ряд примеров `SQL` |
| [streaming](src/main/scala/streaming) | Примеры потоковой передачи |
| [streaming/structured](src/main/scala/streaming/structured) | Примеры структурированных потоков `Spark 2.0`  |
| [graphx](src/main/scala/graphx)       | Ряд примеров `GraphX`  |
| datasource_v2                          | * Новый экспериментальный API для разработки внешних источников данных, начиная с `Spark 2.3.0` - удаляется в пользу нового репозитория [https://github.com/spirom/spark-data-sources](https://github.com/ spirom / spark-data-sources), который подробно изучает новый API. |

