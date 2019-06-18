# Application of Monoids

Here are a few big ideas where monoids play a major role.

## Big Data

In big data applications like Spark and Hadoop we distribute data analysis over
many machines, giving fault tolerance and scalability.
This means each machine will return results over a portion of the data, and we must then combine
these results to get our final result. In the vast majority of cases this can be
viewed as a monoid.

If we want to calculate how many total visitors a web site has received, that
means calculating an Int on each portion of the data. We know the monoid
instance of Int is addition, which is the right way to combine partial results.

## Distributed Systems

In a distributed system, different machines may end up with different views of
data. 

For example, one machine may receive an update that other machines
did not receive. We would like to reconcile these different views, so every
machine has the same data if no more updates arrive. This is called *eventual
consistency*.