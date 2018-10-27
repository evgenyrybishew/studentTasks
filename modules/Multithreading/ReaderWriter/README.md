There is two classes: StringReader, StringWriter. Both use something class Resourse for read and write. But there is 
limit for Writer class, if sombody is reading Resourse, Writer class can not write. And if one Writer Class is writing 
still, other items of StringWrites can not write.
TODO: Only one item can write for resourse in one moment time, and if somebody is writing other items of other classes
(for example StringReader) can not use Resourse.