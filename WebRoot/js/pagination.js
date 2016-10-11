var pageContainer = document.getElementById('pagination');
var array = [];
var page = document.getElementsByClassName('page');
var items = document.getElementsByTagName('tr');

//定义一个数组，存储剔除了第一行（表格栏目行）的剩余元素
var item = [];
for(var i=1,len=items.length; i<len; i++){
item.push(items[i]);
}

var totalItem = item.length;
var eachPage = 5;//
var totalPage = Math.ceil(totalItem/eachPage);//
var currentPage = 1;

if(currentPage=1){
var currentBeginItem = eachPage * (currentPage-1);
//显示当前页应该显示的item
for(var d = currentBeginItem; d < currentBeginItem + eachPage; d++){
	item[d].style.display = "table-row";
}
}
var a = 1;

if (currentPage == 1) {
array[array.length] = "<ul><li><a class=\"page\" href=\"#\" id=\"unclick\">上一页</a></li>";
} else {
array[array.length] = "<ul><li><a class=\"page\" href=\"#\" >上一页</a></li>";
}

for (var a = 1; a <=totalPage; a++) {
array[a] = "<li><a class=\"page\" href=\"#\">" + a +"</a></li>";
}

if (currentPage == totalPage) {
array[array.length] = "<li><a class=\"page\" href=\"#\" id=\"unclick\">下一页</a></li></ul>";
} else {
array[array.length] = "<li><a class=\"page\" href=\"#\" >下一页</a></li></ul>";
}
pageContainer.innerHTML = array.join("");

for (var b = 0; b < page.length; b++) {//对于每一个页码

page[b].onclick = function() {//当页码被点击时			
	currentPage = this.innerHTML;//当前页即为被被点击的页码
	var currentBeginItem = eachPage*(currentPage-1);//当前页最先开始显示的 item
	for (var c = 0; c < item.length; c++) {//清除掉所有item的显示
		item[c].style.display = "none";
	}
	for(var d=currentBeginItem; d<currentBeginItem+eachPage; d++){//显示当前页应该显示的item
		item[d].style.display = "table-row";
	}
};

page[page.length-1].onclick=function(){
	
	if(currentPage!=totalPage){
		currentPage++;//当前页即为被被点击的页码
		var currentBeginItem = eachPage*(currentPage-1);//当前页最先开始显示的 item
		for (var c = 0; c < item.length; c++) {//清除掉所有item的显示
			item[c].style.display = "none";
		}
		for(var d=currentBeginItem; d<currentBeginItem+eachPage; d++){//显示当前页应该显示的item
			item[d].style.display = "table-row";
		}
	}else{
		alert('已经是最后一页');
	}
};

page[0].onclick=function(){
	// alert(0);
	if(currentPage!=1){
		currentPage--;//当前页即为被被点击的页码
		var currentBeginItem = eachPage*(currentPage-1);//当前页最先开始显示的 item
		for (var c = 0; c < item.length; c++) {//清除掉所有item的显示
			item[c].style.display = "none";
		}
		for(var d=currentBeginItem; d<currentBeginItem+eachPage; d++){//显示当前页应该显示的item
			item[d].style.display = "table-row";
		}
	}else{
		alert('已经是第一页');
	}
};
}