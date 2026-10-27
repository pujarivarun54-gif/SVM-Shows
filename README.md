# SVM-Shows - Movie Ticket Booking System

College-level Movie Ticket Booking System using Core Java, plus a static web demo for Vercel.

## Project contents
- `java/` - complete Core Java console application with OOP, collections, exceptions and file handling.
- `index.html`, `style.css`, `app.js` - Vercel-ready browser version of the booking demo.
- `vercel.json` - Vercel static deployment configuration.

## Core Java requirements covered
Classes/objects, constructors, encapsulation, inheritance, polymorphism, abstraction, interfaces, overloading, overriding, arrays/ArrayList, strings, loops, conditions, exception handling, user-defined exceptions, file handling, collections, static and final keywords.

## Run Java
```bash
cd java
javac *.java
java Main
```

Default admin login: `admin@svm.com` / `admin123`

The Java application stores users and bookings under the `data/` directory.

## Vercel
Vercel serves the browser version (`index.html`) as a static site. Core Java console programs cannot run directly as a Vercel web server, so the Java source is included for the college requirement while the browser UI is deployed by Vercel.
