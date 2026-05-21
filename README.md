# Predictive Auto-Shop Management Engine

A robust, enterprise-grade Java application designed to architect, track, and optimize automotive workshop logistics and client-vehicle relations. The system leverages pure Object-Oriented Programming (OOP) design patterns and interfaces with a persistent relational database layer. It features an integrated LLM-powered diagnostic estimation engine for real-time cost projections.

## 🛠️ Tech Stack & Architecture

* **Language:** Java
* **Database Layer:** MySQL / Relational Database Management System (RDBMS)
* **Design Paradigms:** Pure OOP
* **Integrations:** Structured Prompt Engineering for External LLM APIs (Deterministic Cost Estimation Engine)

---

## ⚡ Core Engine Features

### 🧠 LLM-Driven Dynamic Cost Estimation
Instead of relying on rigid, hardcoded price matrices, the application integrates an LLM API configured as a deterministic backend estimation engine. 
* Parses incoming vehicle telemetry, requested manual labor operations, and component requirements.
* Processes variables against a static baseline service rate ($150\text{ RON/hour}$) to synthesize structured, mathematically precise technical disclaimers and total cost projections.

### 🗄️ Relational Entity Data Mapping
* Implements structured data models (`Client`, `Vehicle`, `MaintenanceLog`) managing strict relational integrity.
* Decouples data transport layers from core business logic, ensuring safe data parsing when retrieving state profiles from the persistent MySQL database.
* Features custom state-string formatting overrides (`toString`) optimized for localized terminal rendering and system logs.
