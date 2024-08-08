# Serialization

Encoding an object as a byte stream is known as *serializing* the object;
reverse process is known as *deserializing*

Once an object has been serialized, its encoding can be transmitted from one virtual machine to another.

**A major cost of implementing Serializable is that it decreases the ability to change a class' implementation once it has been released.**
Once it's byte stream encoding becomes part of the exported api you are expected to support serialized form forever.
If you do not go through the effort to implement *custom serialized form*, e.g. accept default, the class's private instance fields become part of its exported api

=> you lose its effectiveness as a tool for information hiding.

**it increases likelihood of bugs and security holes**

**increases testing burden**

So use judiciously! 
