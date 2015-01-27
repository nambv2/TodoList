/**
 * 
 */
//reload page
$(document).ready( function () {
	$("input:checkbox").removeAttr("checked");
	$(window).load(function(){
		$.ajax({
			type: "GET",
			url: "LoadDataServlet",
			dataType: "json",
			success: function(data1) {
					 $.each(data1, function() { 
			            	var output = "<li><div class='view' id="+this.id+">"+
						      "<input class='toggle' type='checkbox'>"+
						      "<label>"+this.name+"</label>"+
						      "<a class='destroy'></a>"+
						    "</div></li>";
			            	$("input:checkbox").removeAttr('checked');
			            	$("ul").append(output);
			            	$("#new-todo").val("");
			            });		
				}
		});
	});
	//enter to do list
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
					var output = "<li><div class='view' id="+key+">"+
				      "<input class='toggle' type='checkbox'>"+
				      "<label>"+input+"</label>"+
				      "<a class='destroy'></a>"+
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
        	$('input:checkbox').removeAttr('checked');
        }

	});

	//mark all
	$("body").on("click","input[type=checkbox]",function(){
		var n = $( "input:checked" ).length;
		if($("#toggle-all").is(":checked")){
			n=n-1;
		}
		var itemDel = "<b id=itemDel>Clear "+n+" completed</b>";
		$("#itemDel").remove();
		$("#clear-completed").append(itemDel);
	});
	
	//event delete all
	$("body").on("click","#itemDel",function(){
		var delAll = "true";
		$.ajax({
			type:"GET",
			data:{"delAll":delAll},
			url:"LoadDataServlet",
			success:function(){
				console.log("Delete all");
				$("input:checkbox").removeAttr("checked");
				$("li").remove();
				$("#count").remove();
				$("#itemDel").remove();
				/*$.each(lstTodo, function() {
					console.log("Delete all");
					$("#todo-list").remove();
					$("footer").remove();
					$("#main").remove();
				});*/

			}
		});
	});
	
	//delete items
	$("body").on("click",".destroy",function(){
		var itemParent = $(this).parent();
		var itemGParent = $(this).parent().parent();
		var valueItem = "";
		var item = $(itemParent).attr("id");
		console.log("id is: "+item);
		$.ajax({
			type:"POST",
			data:{"itemChoose":item,"param":valueItem},
			url:"LoadDataServlet",
			success:function(key){
				var item1 = $("#"+item).parent();
				console.log(item1);
				$("#"+item).remove();
				var countItem = "<b id=count>"+key+" items</b>";
				$("#count").remove();
				$("#todo-count").append(countItem);
				$("#new-todo").val("");
				$(itemGParent).remove();
			}
		});
	});
});