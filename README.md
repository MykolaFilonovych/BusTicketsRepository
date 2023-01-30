# BusTicketsRepository
This is repository with project "Bus tickets".
Посилання для роботи із виконаним завданням.

http://localhost:8080/getTripsList - отримати список всіх рейсів
http://localhost:8080/buyTicket/FIO/tripId - купити квиток, замість FIO має бути прізвище, ім'я, по батькові, замість tripId - ідентифікатор рейсу.
http://localhost:8080/ticketInformation/ticketId - отримати інформацію про квиток, замість ticketId має бути ідентифікатор квитка
http://localhost:8080/scheduleProcessor/ticketId - отримати інформацію про статус платежу квитка, замість ticketId має бути ідентифікатор квитка
http://localhost:8080/getPaymentStatus/paymentId - отримати статус платежу, замість paymentId має бути ідентифікатор платежу

Створення нового платежу відбувається при покупці нового квитка.
У проекті є клас JpaConfig.java, в якому містится інформація для підключення до бази даних з автобусними квитками
(
    url - адреса бази даних,
    username - користувач,
    password - пароль    
).
