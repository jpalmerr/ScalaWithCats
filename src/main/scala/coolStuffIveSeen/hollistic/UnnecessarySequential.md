# Unnecessarily Sequential

In this example I was working with the Monad Transformer: EitherT

```
  val bar = for {
      ccidCatalogueMapping <- EitherT(catalogueMappingClient.getProductionId(productionCCId))
      schedule <- EitherT(fetchAvailability.getSchedules(productionCCId))//        productionId = ccidCatalogueMapping.productionId
      foo <- EitherT.liftF[IO, FetchError, DownStreamAvailability[ProductionCCId]](
      fetchDownstreamAvailabilityFromScheduleResponseXXXXXX(schedule.scheduleData, productionId, productionCCId)(
      fetchAvailability.getCompliance,
      fetchAvailability.getVariants,
      fetchAvailability.getTimecodes,
      fetchAvailability.getRights
      ))
      } yield foo
    
  bar.value
```

Perfectly valid, but described to me as **unnecessarily sequential**.

The same code can be written using mapN (or even parMapN)

```
(EitherT(catalogueMappingClient.getProductionId(productionCCId)), EitherT(fetchAvailability.getSchedules(productionCCId))).mapN {
        case (catalogueMapping, schedule) =>
          EitherT.liftF[IO, FetchError, DownStreamAvailability[ProductionCCId]](
            fetchDownstreamAvailabilityFromScheduleResponseXXXXXX(schedule.scheduleData, catalogueMapping.productionId, productionCCId)(
              fetchAvailability.getCompliance,
              fetchAvailability.getVariants,
              fetchAvailability.getTimecodes,
              fetchAvailability.getRights
            ))
      }.flatten.value
```