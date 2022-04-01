# UBC CPSC 210 Term Project
## Project title: Supermarket Item Management application

The simple application helps to manage items with information including ID, name, quantity, position, in price, out 
price in the supermarket.

### Users
Store, warehouse managers, and cashiers who want to manage the item quantity, the flow in-out of items, and check the 
items with low quantity to prepare for buying more.

### Main functions
1. Adding and storing the item's data **ID**, **Name**, **Count**, **Position**, **inPrice**, **outPrice**  
2. Manipulate with item list and each item parameter including: add and delete item, modify all information of item, view all the item list and sort
the item list by different criteria.

### User stories
- User can add item with the information: **ID**, **Name**, **Count**, **Position**, **inPrice**, **outPrice**
- User can _edit_ all item's data after adding.
- User can _delete_ a specific item in the list.
- User can _view_ all items in the list
- User can _view_ all items that are under user's determined count to prepare for new order.

**Data Persistence**
- When users select quit the application, they have to choose options to save or not save item list.
- When users start the application, they can choose the options to load the saved item list from file or start with the new list.


**Phase 4: Task 2**
Sample log:

Loaded items in My Storefrom ./data/itemList.json

Thu Mar 31 19:12:15 PDT 2022

Added item has name: name test 1

Thu Mar 31 19:12:15 PDT 2022

Added item has name: name test 2

Thu Mar 31 19:12:15 PDT 2022

Added item has name: name test 3

Thu Mar 31 19:12:15 PDT 2022

Added item has name: name test 4

Thu Mar 31 19:12:23 PDT 2022

Filtered all items which are under: 3 count.

Thu Mar 31 19:12:35 PDT 2022

Edited item has order: 1

Thu Mar 31 19:12:50 PDT 2022

Added item has name: Test name

Thu Mar 31 19:12:55 PDT 2022

Deleted item has name: name test 4


**Phase 4: Task 3**
- To improve cohesion, MainGUI could be split up into separate class to implement 
separate functionality, total 6 classes including: 
1. Construct buttons in the main interface
2. Does separate functionality for each button: adding, showing list, sorting, editing, deleting
