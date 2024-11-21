**DESCRIPTION**
I have created a banking app which is monolithic and has security features and is also safe from race conditions.

**FEATURES**:

-Authentication: InBuilt Spring Security mechanism is used for login and a rest api is created to register the user

-Verification: The account can only be used after the admin verifies the account.

-Transaction- User can check their balance, withdrawm, transfer money from the account

-Email - Every transaction will lead to email being sent but the maildev docker container must be started to receive the mail

-Transaction History - All transaction are securely stored




**REQUIREMENTS**:

-Docker

**SETUP**

Run the following command

docker build -t banking-app .

docker compose up -d



