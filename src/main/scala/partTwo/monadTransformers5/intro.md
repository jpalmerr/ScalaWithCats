Imagine we are interacting with a database.

We want to look up a user record.

The user may or may not be present, so we return an Option[User].
 
Our communication with the database could fail for many reasons (network issues,
authentication problems, and so on),

so this result is wrapped up in an Either, giving us a final result of
 
`Either[Error, Option[User]]`

To use this value we need to nest flatmap calls etc... which quickly becomes very tedious.

This is a scenario I run into often at work...

Usage is a really good guide