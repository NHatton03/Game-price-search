# Java CLI Game Price Lookup
## Project Planning Document


## Running the project

from inside the `gamesearch` directory.

```bash
# compile the project
mvn compile exec:java -Dexec.mainClass="com.gamesearch.Main"

# run the project
mvn exec:java -Dexec.mainClass="com.gamesearch.Main"
```

## Overview

### Goal

Build a small, interactive Java command-line application that allows a user to search for a PC game and view its current prices using the GG.deals API.

This project is intended as a learning exercise and should focus on core Java concepts rather than frameworks or advanced architecture.

The application should remain intentionally simple while demonstrating how to:

- Create and manage a Maven project
- Make HTTP requests using the Java Standard Library
- Read configuration from environment variables
- Parse JSON using Jackson
- Interact with a REST API
- Build an interactive console application

---

# Project Scope

## Included

- Interactive command-line interface
- Search for a game by name
- Display matching search results
- Allow the user to select a game
- Retrieve and display current prices from GG.deals
- Allow repeated searches until the user exits

## Excluded

The following are intentionally out of scope for the initial version:

- Error handling
- Logging
- Unit testing
- Configuration files
- Databases
- Caching
- Multiple APIs
- Price history
- Wishlist functionality
- Notifications
- Command-line argument parsing
- CLI frameworks
- Dependency injection
- Spring Boot

---

# Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 21 (or Java 17) | Programming language |
| Maven | Build tool |
| Jackson | JSON parsing |
| Java HttpClient | HTTP requests |
| GG.deals API | Game search and pricing |

The goal is to rely on the JDK wherever possible.

---

# API Configuration

The GG.deals API key should **not** be hardcoded into the application.

Instead, it should be supplied through an environment variable.

This provides a simple introduction to external configuration without introducing additional libraries or configuration files.

The application should assume the required environment variable exists for this initial implementation.

Handling missing or invalid API keys can be added in a future iteration.

---

# User Experience

When the application starts it should:

1. Display a welcome message.
2. Prompt the user to enter a game title.
3. Search GG.deals.
4. Display matching results.
5. Allow the user to select one.
6. Retrieve current prices.
7. Display the prices in a readable format.
8. Ask whether another search should be performed.
9. Exit when requested.

---

# Development Roadmap

## Phase 1 — Create the Project

Objectives:

- Install Java
- Install Maven
- Create an empty Maven project
- Verify the project builds successfully

Deliverable:

A clean project that builds successfully before any application logic is added.

---

## Phase 2 — Add Jackson

Objectives:

- Add the Jackson dependency to the Maven project.
- Verify Maven downloads the dependency successfully.
- Confirm the project still builds successfully.

Deliverable:

A working Maven project with Jackson available for later development.

---

## Phase 3 — Build the Interactive Console

Objectives:

- Display a welcome message.
- Prompt the user for a game title.
- Ask whether another search should be performed.
- Continue looping until the user exits.

At this stage, no API communication is required.

Deliverable:

A functioning interactive command-line interface.

---

## Phase 4 — Configure the API Key

Objectives:

- Create a GG.deals API key.
- Store it as an environment variable.
- Confirm the application can read the environment variable.

No API requests need to be made yet.

Deliverable:

The application can access its API key without hardcoding sensitive information.

---

## Phase 5 — Explore the GG.deals API

Objectives:

Become familiar with:

- the search endpoint
- the pricing endpoint
- request parameters
- response structure

Spend time understanding the API documentation before implementing requests.

Deliverable:

A clear understanding of the API responses that will need to be parsed.

---

## Phase 6 — Connect to the API

Objectives:

- Send HTTP requests using the Java HttpClient.
- Retrieve responses from the GG.deals API.
- Confirm communication with the API is successful.

No JSON parsing is required yet.

Deliverable:

Successful communication with the API.

---

## Phase 7 — Parse API Responses

Objectives:

Use Jackson to convert the JSON responses into Java objects.

Focus on understanding:

- JSON structure
- nested objects
- arrays
- object mapping

Deliverable:

API responses represented as Java objects.

---

## Phase 8 — Search for Games

Objectives:

Allow users to:

- enter a game title
- retrieve matching results
- display those results in a numbered list

Deliverable:

Interactive game searching.

---

## Phase 9 — Select a Game

Objectives:

Allow the user to choose one of the returned search results.

Use that selection to request pricing information.

Deliverable:

Game selection working from the console.

---

## Phase 10 — Display Prices

Objectives:

Display the returned pricing information in a clean, readable format.

Include:

- Store name
- Current price

Keep formatting simple and suitable for a terminal.

Deliverable:

A complete end-to-end application.

---

# Maven Project Setup

## Step 1

Install:

- Java
- Maven

Verify both installations from the terminal before creating the project.

---

## Step 2

Create a new Maven project using the standard Maven quickstart archetype.

This provides:

- the standard Maven directory structure
- a `pom.xml`
- a starting point for the project

---

## Step 3

Remove the sample files generated by the archetype.

Begin with an intentionally clean project.

---

## Step 4

Build the project without making any changes.

This verifies that Maven is installed correctly.

---

## Step 5

Add the Jackson dependency to the `pom.xml`.

Build the project again to confirm:

- dependency resolution works
- Maven downloads the required libraries
- the project still builds successfully

---

## Step 6

Begin implementing the application according to the development roadmap above.

---

# Learning Objectives

By completing this project you should become comfortable with:

- Maven fundamentals
- Managing dependencies
- Building Java projects
- Reading environment variables
- Using Java's HttpClient
- Working with REST APIs
- Understanding JSON
- Parsing JSON with Jackson
- Creating an interactive console application
- Incrementally building a small software project

---

# Definition of Done

The project is complete when a user can:

- Launch the application.
- Enter a game title.
- View matching search results from GG.deals.
- Select one of the returned games.
- View the current prices from available stores.
- Perform additional searches without restarting the application.
- Exit the application cleanly.

At that point, the project has met its learning goals while remaining intentionally small and focused.
