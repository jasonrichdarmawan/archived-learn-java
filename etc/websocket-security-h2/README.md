### To Do:

- [X] Secured CONNECT
  - [X] ws://localhost:8080/chat
- [ ] Secured SUBSCRIBE
  - [ ] ws://localhost:8080/user
  - [ ] ws://localhost:8080/topic/channel/{id}
- [ ] Secured SEND
  - [ ] ws://localhost:8080/app/user/{userName}
  - [ ] ws://localhost:8080/app/channel/{id}
  
### 2 To Do:

- [ ] Queued Message `A user can receive the message sent by another user when the user was offline.`
  ```
  If a user has not received the message, the message should be stored in a broker.
  If all user has received the message, the message should no longer be stored in a broker.
  The message will be deleted after 14 days.
  ```