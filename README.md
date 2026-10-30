# Bus Ticket Booking System

Backend API for selling seats on intercity buses, built with Spring Boot, Spring Data JPA and PostgreSQL.

## Student

| | |
|---|---|
| **Name** | Byiringiro Ingabire Aime |
| **Student ID** | 27216 |
| **Project** | bus-ticket-booking-system |
| **Course** | INSY 8322 Web Technology and Internet (AUCA), Mid-Term Project |

## Technologies

- Java, Spring Boot (Spring Web, Spring Data JPA)
- PostgreSQL
- Maven (Maven Wrapper included)
- Postman (for testing)

## Entities

Route, Bus, Driver, Passenger, Booking, Luggage, and the enum `BookingStatus` (RESERVED, PAID, BOARDED, CANCELLED).

## How to run the API

1. **Install the requirements:** JDK (the version set in `pom.xml`), PostgreSQL, and Git.

2. **Clone the repository:**
   ```
   git clone https://github.com/<your-username>/bus-ticket-booking-system-backend.git
   cd bus-ticket-booking-system-backend
   ```

3. **Create the database** in PostgreSQL:
   ```sql
   CREATE DATABASE bus_ticket_booking_system;
   ```

4. **Configure** `src/main/resources/application.properties` with your own database name, username and password:
   ```properties
   server.port=8003
   spring.datasource.url=jdbc:postgresql://localhost:5432/bus_ticket_booking_system
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

5. **Start the application:**
   - Windows: `mvnw.cmd spring-boot:run`
   - Linux / macOS: `./mvnw spring-boot:run`

6. The API runs at **http://localhost:8003**. The tables are created automatically on the first start.

## Testing with Postman

1. Open Postman and click **Import**.
2. Select `bus-ticket-booking-system-midterm.postman_collection.json` from the root of this repository.
3. In the collection's **Variables** tab, replace the `PASTE_..._ID` values with real UUIDs from your database.
4. Enter the seed data first through the save endpoints (for example `POST /api/routes/save`), then run the requests.

The collection has one request per requirement, named by its ID (A2, B1, C4, D2, ...).

## Endpoints

| Part | ID | Endpoint |
|---|---|---|
| A | A2 | `POST /api/{path}/save`, `GET /api/{path}/all`, `GET /api/{path}/{id}`, `PUT /api/{path}/update/{id}`, `DELETE /api/{path}/delete/{id}` where `{path}` is routes, buses, drivers, passengers or bookings |
| B | B1 | `GET /api/passengers/by-last-name?lastName=` |
| B | B2 | `GET /api/bookings/by-status?status=` |
| B | B3 | `GET /api/bookings/between?start=&end=` |
| C | C1 | `GET /api/drivers/by-route?name=` |
| C | C2 | `GET /api/buses/free` |
| C | C3 | `GET /api/routes/unused` |
| C | C4 | `GET /api/passengers/of-driver/{driverId}` |
| D | D1 | `POST /api/bookings/save` |
| D | D2 | `PATCH /api/bookings/{id}/status?status=` |
| D | D3 | `PUT /api/drivers/{driverId}/routes/{routeId}` |
| D | D4 | `PATCH /api/drivers/{driverId}/bus?busNumber=` |
| E | E1 | `POST /api/luggage-items/save?bookingId=` |
| E | E2 | `GET /api/luggage-items/passenger/{nationalId}` |
| E | E3 | `DELETE /api/luggage-items/delete/{id}` |
| F | F1 | `GET /api/bookings/stats/by-status` |
| F | F2 | `PATCH /api/bookings/cancel-day?driverId=&date=` |
| Bonus | +5 | `GET /api/dashboard/summary` |
| Bonus | +5 | `GET /api/bookings/page?page=0&size=5&sort=travelDate,desc` |
