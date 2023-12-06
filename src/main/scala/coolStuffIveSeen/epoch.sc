import java.time.LocalDate

val d1 = LocalDate.of(2023,10,10)
d1.toEpochDay

val d2 = LocalDate.of(2023, 10, 20)
d2.toEpochDay

d1.toEpochDay.to(d2.toEpochDay)

d1.toEpochDay.to(d2.toEpochDay).map(LocalDate.ofEpochDay)

d2.toEpochDay.to(d1.toEpochDay).map(LocalDate.ofEpochDay)