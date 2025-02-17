

				</div>


			</div>

		</div>

	</div>


	


  	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>

	<script src="${contextRoot}/js/bootstrap.min.js"></script>
	
	<sec:authorize access="isAuthenticated()">
		<script>
			connectionManager.connect();
		</script>
	</sec:authorize>
	
	
	
	 <script>
    
    var pagesFetched = 1;
    
    function sizeChatWindow() {
    	$('#chat-message-record').height(0);
    	
    	var documentHeight = $(document).height();
    	
    	var sendMessageHeight = $('#chat-message-send').height();
    	var messageRecordPos = $('#chat-message-record').offset().top;
    	var panelBodyPadding = 15;
    	
    	var messageRecordHeight = documentHeight - (messageRecordPos + sendMessageHeight + 2 * panelBodyPadding);
    	$('#chat-message-record').height(messageRecordHeight);
    }
    
    function newMessageCallback(message) {
    	addMessage(JSON.parse(message.body), true);
    }
    
    function addMessage(message, isNew) {
    	var text = message.text;
    	var isReply = message.isReply;
    	
    	var className = isReply ? 'chat-message-reply' : 'chat-message-sent';
    	
    	var div = document.createElement('div');
    	div.className = 'chat-message ' + className;
    	div.innerHTML = text;
    	
    	if(isNew) {
    		$('#chat-message-record').prepend(div);
    	} 
    	else {
    		$('#chat-message-record').append(div);
    	}
    	
    }
    
    connectionManager.addSubscription("${inboundDestination}", newMessageCallback);
    
    function sendMessage() {
    	
    	var text = $("#chat-message-text").val();
    	
    	var message = {
    		'text': text	
    	};
    	
    	connectionManager.send("${outboundDestination}", message);
    	
    	
    	$("#chat-message-text").val("");
    	$("#chat-message-text").focus();
    }
    
    
    function addPreviousMessages(messages) {
    	
    	for(var i=0; i<messages.length; i++) {
			addMessage(messages[i], false);
		}
    	
    	pagesFetched++;
    	
    	$('#chat-message-record').animate({scrollTop: 0}, 800);
    }
    
	$(document).ready(function() {
		
		$(document).keypress(function(e) {
			if(e.which == 13) {
				
				// Enter key pressed
				sendMessage();
				
				return false;
			}
		});
		
		$('#chat-send-button').click(function(){
			sendMessage();
		});
		
		$('#chat-older-messages').click(function() {
			
			connectionManager.fetchMessages("${conversationAjaxUrl}", addPreviousMessages, pagesFetched);
		});
		
		sizeChatWindow();
		$(window).resize(sizeChatWindow);
	});
	
	
	function refreshMessages(messages) {
		for(var i=0; i<messages.length; i++) {
			addMessage(messages[i], false);
		}
	}
	
	connectionManager.fetchMessages("${conversationAjaxUrl}", refreshMessages, 0);
	
    </script>

  </body>
</html>