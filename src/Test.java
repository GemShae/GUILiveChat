/* case ServerRequest.USER_LIVE_CHAT_COMMAND -> {
                    //Actions to log on live chat
                    ServerResponse response = logOnUserToLiveChat(action);
                    objectOutputStream.writeObject(response);
                }
                case ServerRequest.USER_END_CHAT_COMMAND -> {
                    //Actions to log the user off the live chat
                    ServerResponse response = logOffUserFromLiveChat(action);
                    objectOutputStream.writeObject(response);
                }
                case ServerRequest.USER_SEND_MESSAGE_LIVE_CHAT_COMMAND -> {
                    _Message message = (_Message) action.getData();
                    //Actions to send message

                    //Search for the recipient of the message in the connected clients list
                    for (MultipleClientHandler client:Server.activeClients) {
                        //Check if the recipient is an emplotyyee
                        for (_User onlineUser: Server.activeLiveChatUsers) {
                            if (message.getRecipientId() == onlineUser.getUserID() ) {
                                //Send message to that employee
                                ServerResponse response;
                                String responseMessage = "Incoming message";
                                int code = ServerResponse.REQUEST_SUCCEEDED;
                                response = new ServerResponse<_Message>(responseMessage,code,message);
                                client.objectOutputStream.writeObject(response);
                                break;
                            }
                        }
                    }
                    //Save the message to the database
                    //Driver.messageRepository.save(message);
                }

               ServerResponse logOnUserToLiveChat(ServerRequest action) {
        //Check if user is a Customer or an Employee of type
        ServerResponse response = null;
        _User user = (_User) action.getData();

        //if Customer
        if (user.getClass().getSimpleName().equals("Customer")) {
            //Add customer to current list of online customers and change the status to online
            user.setIsOnline(true);
            Server.activeLiveChatUsers.add(user);

            String message = "Login Successful";
            int code = ServerResponse.REQUEST_SUCCEEDED;
            response = new ServerResponse<ArrayList<_User>>(message,code, Server.activeLiveChatUsers);

        }else if (user.getClass().getSimpleName().equals("Employee")) {
            //Check for the type of Employee, It should be a Technician
            Employee employee = (Employee) user;

            if (employee.getRole().equals("Technician")) {
                //Add Technician to current list of online technicians and change the status to online
                user.setIsOnline(true);
                Server.activeLiveChatUsers.add(user);

                String message = "Login Successful";
                int code = ServerResponse.REQUEST_SUCCEEDED;
                response = new ServerResponse<ArrayList<_User>>(message,code,Server.activeLiveChatUsers);

            }else if (employee.getRole().equals("Customer Service Rep") || employee.getRole().equals("Admin")) {
                //Don't allow them to log on to live chat

                String message = "Login Failed";
                int code = ServerResponse.REQUEST_FAILED;
                response = new ServerResponse<_User>(message,code,user);
            }
        }
        return response;
    }

    ServerResponse logOffUserFromLiveChat(ServerRequest action) {
        //Check if user is a Customer or an Employee of type
        _User user = (_User) action.getData();

        user.setIsOnline(false);
        Server.activeLiveChatUsers.remove(user);

        ServerResponse response;
        String message = "Log Out Successful";
        int code = ServerResponse.REQUEST_SUCCEEDED;
        response = new ServerResponse<>(message,code, user);
        return response;
    }

 */