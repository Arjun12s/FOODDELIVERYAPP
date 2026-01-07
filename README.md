# 🍔 Multi-Restaurant Food Delivery System

This project implements a production-grade order management architecture for a food delivery application. It is designed to support **single-checkout, multi-restaurant ordering**, where a customer can place one order containing items from multiple restaurants, while each restaurant independently processes and fulfills its portion of the order.

The system focuses on **data integrity, scalability, and real-world operational accuracy**, following patterns used by large food delivery platforms.

---

## 📌 Core Concept

In real-world food delivery systems, a single customer order does not translate into a single restaurant task. Each restaurant prepares food independently, tracks its own order status, and may accept or reject orders separately. To support this, the system uses a **hierarchical order structure** that separates:

- Customer intent (checkout)
- Restaurant execution
- Item-level preparation

---

## 🧱 Architecture Overview

The system is organized into four logical layers:

1. **Cart Layer (Session-Based)**
2. **Master Order Layer**
3. **Restaurant Order Layer**
4. **Order Item Layer**

This separation ensures clean data flow and prevents cross-restaurant conflicts.

---

## 🛒 Cart Design

The cart is grouped by restaurant rather than being a flat list of items.  
Each restaurant maintains its own sub-cart inside the main cart.

This design allows:
- Accurate price calculation per restaurant
- Clean order splitting during checkout
- Support for different delivery rules per restaurant

---

## 📦 Order Management Design

### 1. Master Order

The master order represents a **single checkout action by the user**.  
It stores:
- User information
- Delivery address
- Total payable amount
- Payment status
- Overall order status

The master order **does not belong to any restaurant**. It exists purely to represent the customer’s intent and payment record.

---

### 2. Restaurant Orders

For each restaurant involved in the checkout, a **separate restaurant order** is created.

Each restaurant order:
- Is linked to the master order
- Contains only items belonging to that restaurant
- Maintains its own status lifecycle (Accepted, Preparing, Ready, Delivered)
- Can be cancelled or refunded independently

This enables decentralized fulfillment while preserving centralized checkout.

---

### 3. Order Items

Order items represent the actual food items being prepared.

Each order item:
- Belongs to exactly one restaurant order
- Stores quantity and price at the time of ordering
- Prevents cross-restaurant data leakage

This ensures billing accuracy and reliable order history.

---

## 💳 Payment Flow

- The user completes **one payment** for the entire checkout.
- Payment status is recorded at the master order level.
- Restaurant orders are activated only after successful payment.
- Refunds and cancellations can be handled per restaurant if required.

---

## ✅ Key Benefits

- Supports multiple restaurants in a single order
- Enables independent restaurant workflows
- Prevents data inconsistency and item mixing
- Scales cleanly as the platform grows
- Aligns with real-world food delivery operations

---

## 🚀 Ideal Use Cases

- Food delivery platforms
- Cloud kitchen aggregators
- Multi-vendor ordering systems
- Restaurant marketplace applications

---

## 🛠️ Technology Stack (Example)

- Backend: Java (Servlets / JSP), DAO pattern
- Database: MySQL / PostgreSQL
- Session Management: HTTP Session
- Frontend: JSP / HTML / CSS
- Mapping: OpenStreetMap (Leaflet)

*(Stack can be adapted to Spring Boot, Node.js, or other frameworks.)*

---

## 📖 Future Enhancements

- Real-time order tracking
- Delivery partner assignment
- Payment gateway integration
- Admin and restaurant dashboards
- Analytics and reporting

---

## 📄 License

This project is open for learning and educational purposes.  
You are free to extend and adapt the architecture for your own applications.
