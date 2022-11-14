```
Author: manticorevenom
Version: 1.0 (Current)
Date: 2022.11.13
```
# Format Specifications
```NOTE:``` The document and the code must be formatted in order for the program to work correctly. 
If either the document or the code does not meet the format specifications, the program
will not work correctly.

# Document Format Specifications
The document must have an identifier for tracking items. The identifier is user-specified.
We recommend that this identifier is unique such that it should not appear in the document
except for tracking purposes.

For example, we use ```SFREQ``` as our identifier in our document examples. This stands for Software Functional
Requirement.

The identifier must be followed by the tag for the item. Tags should be unique meaning that they
will not be re-used.

For example, one of our tags for an item is ```BookList.Add```.

Items should also have some context following the tag, this is a recommendation and not a requirement.
Not having context for items should not impact the functionality of the program.

After the context, items should have some source code that is related to the item.
We recommend using method headers, class headers, and class variables.

```NOTE:``` You can have multiple lines of code for a single item.

For example, our item ```BookList.Add``` has related method, ```public void add()```. This code should
be directly under the item. The code is used to validate that the document reflects the source-code
and vice-versa.

To summarize the following is the formal specification,
```
<Identifier> <Item_Tag> <Some_Item_Context>
    <Source_code>
```

Here is an example of this,
```
SFREQ BookList.Add - The application shall be able to add a book to the inventory.
    public void add()
    private boolean search()
```
# Code Format Specifications
The code must have an identifier for tracking items. The identifier is user-specified.
We recommend that this identifier is unique such that it should not appear in the code
except for tracking purposes.

For example, we use ```<FREQ>``` as our identifier in our code examples. This stands for Functional
Requirement. Our identifier also has an end tag, which is ```</FREQ>``` in our examples. 

Tags should be between the identifier and the end tag for the identifier, these tags should be the same as
the tags used in the document. For example, ```BookList.Add``` is the tag in the document but is also the tag in the
code.

Here is an example,
```
/**
* <FREQ>BookList.Add</FREQ>
*/
```

Identifiers with tags should be located in comments, and should be placed above each line of code
where applicable.

For example, the above comment would be placed above the ```public void add()``` method.

Some lines of code may correspond to more than one item, just like in the document. To support this,
multiple tags can be used between the identifiers.

An example of this,
```
/**
* <FREQ>BookList.Add::BookList.AddCopy::BookList.ProcessCheckout::BookList.ProcessReturn::BookList.Delete::BookList.DeleteCopy</FREQ>
* @param field
* @return new string
*/
```

Each tag should be separated by a double colon, ```::```.

Each line of code that corresponds to an item should be marked with the tag used in the document. 
This allows the program to track items correctly. 

Here is the formal code specification,

<b>Single Tag</b>:
```
/**
* <Identifier><Tag></Identifier>
*/
<Some Line of Code>
```
An example,
```
/**
* <FREQ>BookList</FREQ>
*/
private ArrayList<Book> inventory;
```
Another Example,
```
/**
* <FREQ>BookList.Add</FREQ>
*/
public void add(){
```
<b>Multiple Tags</b>:



