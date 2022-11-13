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

For example, one of our tags for a requirement is ```BookList.Add```.

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
We recommend that this identifier is unique such that it should not appear in the document
except for tracking purposes.

For example, we use ```<FREQ>``` as our identifier in our code examples. This stands for Functional
Requirement.