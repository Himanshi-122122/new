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
