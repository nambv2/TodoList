/**
 * 
 */

$(document).ready( function () {
	
	
	$(window).load(function(){
		$.ajax({
			type: "GET",
			url: "LoadDataServlet",
			dataType: "json",
			success: function(data1) {
					 $.each(data1, function() { 
			            	var output = "<li><div class='view'>"+
						      "<input class='toggle' type='checkbox'>"+
						      "<label>"+this.name+"</label>"+
						      "<a class='destroy'></a>"+
						    "</div></li>";
			            	$("ul").append(output);
			            	$("#new-todo").val("");
			            });		
				}
		});
	});
	
	$("body").on("keypress","#new-todo", function (e) {
		if(e.which == 13){
			
			console.log("enter");
			var input = $("#new-todo").val();
			console.log(input);
			$.ajax({
				type: "POST",
				data: {"param" : input},
				url: "LoadDataServlet",
				success: function(key) {	
					console.log(key+"--------1111");
					var output = "<li><div class='view'>"+
				      "<input class='toggle' type='checkbox'>"+
				      "<label>"+input+"</label>"+
				    "</div></li>";
					var countItem = "<b id=count>"+key+" items</b>";
					$("ul").append(output);
					$("#count").remove();
					$("#todo-count").append(countItem);
					$("#new-todo").val("");
					
				}
			});
		}
	});	
	
	$("input[type=checkbox]").on("click",function(){
		if ($("#toggle-all").is(":checked"))
        {
          $("input:checkbox").not(this).prop("checked", this.checked);
          
        } else {
        	$("input:checkbox").removeAttr("checked");
        }

	});

	$("body").on("click","input[type=checkbox]",function(){
		var n = $( "input:checked" ).length;
		if($("#toggle-all").is(":checked")){
			n=n-1;
		}
		var itemDel = "<b id=itemDel>Clear "+n+" conpleted</b>";
		$("#itemDel").remove();
		$("#clear-completed").append(itemDel);
	});		  
	
	$("body").on("change","input[type=checkbox]",function(){
		var classParent = $(this).parent().get(0).className;
		var nameParent = $(".toggle").closest("."+classParent);
	    if ($(this).prop("checked")){
	        console.log(nameParent);
	        }
	});
	
});