# Predictive Auto-Shop Management Engine

A high-performance C++ platform built to model, track, and optimize automotive workshop logistics. The application uses object-oriented data structures to manage relational integrity between mechanics, customers, vehicle fleets, and active maintenance logs.

## 🛠️ Tech Stack & Key Features

* **Language:** C++ (Standard ISO/IEC 14882)
* **Paradigm:** Pure Object-Oriented Programming (Polymorphism, Encapsulation)
* **Persistence Layer:** Flat-file database emulation using custom sequential C++ File Stream APIs (`fstream`)

---

## ⚡ Functional Capabilities

* **Relational Entity Mapping:** Tracks cross-references between dynamic vehicle instances and customer ownership profiles without relying on a heavyweight database engine.
* **Algorithmic Cost Matrix:** Features an internal deterministic pricing engine that processes diagnostic variables, replacement parts overhead, and dynamic labor hourly rates to compute instant financial repair projections.
* **State Persistence:** Seamlessly flushes the structural memory hierarchy down to persistent storage and loads it back on initialization without structural corruption.
