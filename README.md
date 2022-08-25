# My Personal Project: Custom Earring Pre-Order App

## CustomEar: A Custom Earring Pre-Order App
*CustomEar* is a custom earring pre-order app for **customers who love handcrafted earrings** 
so that they can easily and efficiently pre-order custom earrings.

In this app, customers can:
- add earrings to their order
- choose from different earring customization options
- view the items in their order
- remove items from their order
- view the total price of their order

I want to create this app because I enjoy making handmade jewelry and it would be nice to have a free app
where customers can request custom earrings. This way, I can review them before charging them and starting
their order.

## User Stories
- As a user, I want to be able to add an item to my order.
- As a user, I want to be able to remove an item from my order.
- As a user, I want to be able to view all earrings in my order.
- As a user, I want to be able to view the total price of my order.
- As a user, I want to be able to save my order to file.
- As a user, when I start the application, I want to be given the option to load my order from file.

## Instructions for Grader

All the UI can be found and played from CustomEarUI.

- You can view all the earrings in the order (where X's added to Y's are displayed) by:
    - going to main dashboard
    - then, clicking on "View All Earrings" button. A new frame will open.
  
- You can add earrings to the order (first required event: button press for add multiple X's to a Y) by:
  - going to main dashboard (accessible after you either load a previous order or create a new order)
  - then, clicking on "Add Earring" button. A new frame will open.
  - then, selecting the metal type, charm type, and quantity
  - then, clicking on "Done" button

- You can remove an earring (second required event: select a menu item) by:
  - going to main dashboard
  - then, clicking on "View All Earrings" button. A new frame will open.
  - then, clicking on the earring/item you want to remove (your click will select the item)
  - then, clicking the menu, "Remove"
  - then, selecting menu item "Remove Selected Earring"
  - (note: another option is clicking on "Remove" button in this frame; I actually implemented this button first
     before I read the criteria more carefully)

- You can locate my visual component by:
  - going to the main dashboard
  - there is an image of the CustomEar logo in the header
  - there is also an image of the types of charms as a placeholder next to the "Save Order" button

- You can save the state of my application by:
  - going to main dashboard
  - clicking on "Save Order" button

- You can reload the state of my application by:
  - at the start of the app, you will get option to load or not
  - click "Yes" button to load from file. 
  
  If you don't already have a saved file, the app will prompt you to create a new order. 
  If you create a new order, you can press "Save Order" button on main dashboard and 
  then exit the application. When you restart, you can now reload the state.

## Phase 4: Task 2

- Wed Aug 10 11:53:41 PDT 2022
  Earrings added to order.
- Wed Aug 10 11:53:44 PDT 2022
  Earrings removed from order.
- Wed Aug 10 11:53:48 PDT 2022
  Earrings added to order.
- Wed Aug 10 11:53:52 PDT 2022
  Earrings added to order.
- Wed Aug 10 11:53:55 PDT 2022
  Earrings removed from order.
- Wed Aug 10 11:53:58 PDT 2022
  Earrings removed from order.

Note to grader: to quit the application, you have to close the main dashboard.

## Phase 4: Task 3
- to improve cohesion, I'd create a separate "Main" class in the ui package instead of calling main
  in the CustomEarUI
- to improve cohesion + clarity, I'd create separate classes for these frames that also 
  contain their components:
  - loadOption 
  - mainDashboard
  - newOrderFrame
  - earringOrderFrame
  - displayEarringFrame
- to improve cohesion, I could add an Account class that stores the name and telephone number
  for the order
