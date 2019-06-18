# Monoid Exercise

Monoids for booleans:

**And**:

- *Operator* && 
- *Identity* true

Associative as:

```$xslt
true && false = false
false && true = false
(a && b) && c = a && (b && c) 
```

Identity *true* as:

```$xslt
true = true 
true && true = true // => identity
false && true = false // changes result => not identity
a && true == true && a
```

**Or**:

- *Operator* ||
- *Identity* false

Associative as:

```$xslt
(a || b) || c == a || (b || c)
```

Identity *false* as:
```$xslt
a || False == False || a
```