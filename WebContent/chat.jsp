<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>User Interface</title>
        <!-- Description, Keywords and Author -->
        <meta name="description" content="Your description">
        <meta name="keywords" content="Your,Keywords">
        <meta name="author" content="ResponsiveWebInc">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Styles -->
        <!-- Bootstrap CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Font awesome CSS -->
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="css/style-5.css" rel="stylesheet">
        <!-- Favicon -->
        <link rel="shortcut icon" href="#">
    </head>
    <body>
        <div id="addContactModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Add Contact</h4>
                    </div>
                    <div class="modal-body">
                        <input id="addContactEmail" type="email" class="form-control" placeholder="Email"/>
                        <div id="addContactError"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-lblue" onClick="addContact()">Add</button>
                        <button type="button" class="btn btn-lblue" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <br />
        <br />
        <div class="ui-5">
            <!-- Profile window -->
            <div class="ui-window">
                <div class="row">
                    <div class="col-md-4 col-sm-4">
                        <!-- Contact Heading -->
                        <div class="row">
                            <div class="col-md-6">
                                <h3><i class="fa fa-user lblue"></i>&nbsp; All Contacts</h3>
                            </div>
                            <div class="col-md-6">
                                <button class="btn btn-lblue" type="button" data-toggle="modal" data-target="#addContactModal">Add Contact</button>
                            </div>
                        </div>
                        <!-- Profile user chat contacts -->
                       
                        <div id="contactList" class="chat-contact">
<!--                        
	                        <div class="chat-member">
                                <div class="img-container">
                                    <a href="#"><img class="mg-responsive" src="img/user/2.jpg" alt="" /></a>
                                    <span class="st-live status"></span>
                                </div>
                                <h4><a href="#">Barbara Warts</a></h4>
                                <p>I am online now.</p>
                                <div class="clearfix"></div>
                            </div>
                              
                            <div class="chat-member">
                                <div class="img-container">
                                    <a href="#"><img class="mg-responsive" src="img/user/3.jpg" alt="" /></a>
                                    <span class="st-hide status"></span>
                                </div>
                                <h4><a href="#">Medona Doe</a></h4>
                                <p>3 hours ago.</p>
                                <div class="clearfix"></div>
                            </div>
                            <div class="chat-member">
                                <div class="img-container">
                                    <a href="#"><img class="mg-responsive" src="img/user/4.jpg" alt="" /></a>
                                    <span class="st-off status"></span>
                                </div>
                                <h4><a href="#">July Doe</a></h4>
                                <p>8 hours ago.</p>
                                <div class="clearfix"></div>
                            </div>
                            <div class="chat-member">
                                <div class="img-container">
                                    <a href="#"><img class="mg-responsive" src="img/user/5.jpg" alt="" /></a>
                                    <span class="st-live status"></span>
                                </div>
                                <h4><a href="#">Henry Gates</a></h4>
                                <p>I am online now.</p>
                                <div class="clearfix"></div>
                            </div>
                            <div class="chat-member">
                 
                                <div class="img-container">
                                    <a href="#"><img class="img-responsive" src="img/user/6.jpg" alt="" /></a>
                                    <span class="st-busy status"></span>
                                </div>
                                <h4><a href="#">Jennifer Loren</a></h4>

                                <p>I am busy now. </p>
                                <div class="clearfix"></div>
                            </div>

                            <div class="chat-member">
                                <div class="img-container">
                                    <a href="#"><img class="mg-responsive" src="img/user/7.jpg" alt="" /></a>
                                    <span class="st-live status"></span>
                                </div>
                                <h4><a href="#">James Louis</a></h4>
                                <p>I am online now.</p>
                                <div class="clearfix"></div>
                            </div>

                            <div class="chat-member">
                                <div class="img-container">
                                    <a href="#"><img class="mg-responsive" src="img/user/3.jpg" alt="" /></a>
                                    <span class="st-hide status"></span>
                                </div>
                                <h4><a href="#">Medona Doe</a></h4>

                                <p>3 hours ago.</p>
                                <div class="clearfix"></div>
                            </div>

                            <div class="chat-member">
                                <div class="img-container">
                                    <a href="#"><img class="mg-responsive" src="img/user/4.jpg" alt="" /></a>
                                    <span class="st-off status"></span>
                                </div>
                                <h4><a href="#">July Doe</a></h4>
                                <p>8 hours ago.</p>
                                <div class="clearfix"></div>
                            </div>
-->                   
                        </div>
                    </div>
                    <div class="col-md-8 col-sm-8">
                        <h3><i class="fa fa-comments-o lblue"></i>&nbsp; Live Chat</h3>
                        <!-- Profile chat content -->
                        <div id="messagesList" class="chat-content" style="min-height:400px">
<!--                         
                            <div class="chat-box chat-in">
                                <div class="img-container">
                                    <img class="img-responsive" src="img/user/4.jpg" alt="" />
                                </div>
                                <div class="message">
                                    <h5><i class="fa fa-clock-o"></i>&nbsp; 4:30 AM, May 25</h5>
                                    <p>Consider donating a small sum to help pay for keep the site on the for Internet, please the hosting and bandwidth bill.</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="chat-box chat-out">
                                <div class="img-container">
                                    <img class="img-responsive" src="img/user/2.jpg" alt="" />
                                </div>
                                <div class="message">
                                    <h5><i class="fa fa-clock-o"></i>&nbsp; 4:32 AM, May 25</h5>
                                    <p>Consider donating a small sum to help pay for keep the site on the for Internet, please the hosting and bandwidth bill.</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
 -->                            

                        </div>
                        <!-- Chatting message input box -->
                        <div class="chat-input-box">
                            <div class="input-group">
                                <input id="chatMessage" type="text" class="form-control" placeholder="Type your message">
                                <span class="input-group-btn">
                                <button onclick="sendChatMessage()" class="btn btn-lblue" type="button">Send</button>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Javascript files -->
        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap JS -->
        <script src="js/bootstrap.min.js"></script>
        <!-- jQuery nice scroll (For smooth scrolling and scrollbar) -->
        <script src="js/jquery.nicescroll.min.js"></script>
        <!-- Respond JS for IE8 -->
        <script src="js/respond.min.js"></script>
        <!-- HTML5 Support for IE -->
        <script src="js/html5shiv.js"></script>
        <script>
            /* Scroll bar JS */
            
            $(".chat-contact").niceScroll({
            	cursorcolor:"#999",
            	cursoropacitymin:0,
            	cursoropacitymax:0.3,
            	cursorwidth:5,
            	cursorborder:"0px",
            	cursorborderradius:"0px",
            	cursorminheight:50,
            	zindex:1,
            	mousescrollstep:20
            });
            
            $(".chat-content").niceScroll({
            	cursorcolor:"#999",
            	cursoropacitymin:0,
            	cursoropacitymax:0.3,
            	cursorwidth:5,
            	cursorborder:"0px",
            	cursorborderradius:"0px",
            	cursorminheight:50,
            	zindex:1,
            	mousescrollstep:20
            });
            
        </script>
        <script>
        	var userId = <%=request.getAttribute("userId") %>;
        </script>
        <script src="js/chat.js"></script>
    </body>
</html>
