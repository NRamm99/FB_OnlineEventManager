# 🎟️ EventManager – Console-Based Java Event Management System

### 🧩 Overview
**EventManager** is a console-based Java application designed to simulate an online event management system.  
It allows users to **create, manage, and join events** (such as concerts, LAN parties, and workshops) — while demonstrating how core **software design patterns** can make the system flexible, scalable, and maintainable.

This project was developed as part of a programming assignment focused on understanding and implementing **Object-Oriented Design Patterns** in Java.

---

## 🧠 Learning Goals
- Understand what **design patterns** are and why they are used.  
- Learn to identify where patterns fit naturally in a system.  
- Implement key patterns such as **Singleton**, **Factory**, **Observer**, and **Decorator**.  
- Reflect on design decisions and their impact on maintainability.

---

## ⚙️ Features
### 👤 User Management
- Users can **register** and **log in**.  
- **Admins** can add, remove, or update user profiles.  
- Singleton pattern ensures there is only one active `UserManager` instance controlling all users.

### 🎫 Event Administration
- Users can **create** and **view** events.  
- Different event types (Concert, Workshop, Conference, etc.) are created dynamically using the **Factory Pattern**.  
- Events include title, description, date, and location.

### 🔔 Registration & Notifications
- Users can **register** for events.
- When an event changes or is cancelled, all registered users are **notified automatically** via the **Observer Pattern**.

### 🌟 Event Add-ons (Decorator Pattern)
- Events can be **decorated** with optional extras like:
  - `VipAccess`
  - `MealService`
  - `BackstagePass`
- Each decorator adds functionality and modifies the total description/price dynamically.

### 📊 Reporting (Admin Only)
- Admins can generate summaries of all events and participants.
- Example: “Workshop: 15 participants — 3 VIP upgrades”