let table = document.createElement('table');
let thead = document.createElement('thead');
let tbody = document.createElement('tbody');


table.appendChild(thead);
table.appendChild(tbody);

document.getElementById('info').appendChild(table);

// let result_table= document.createElement('table');
result_table= document.getElementById('results');
let result_thead = document.createElement('thead');
let result_tbody = document.createElement('tbody');


// result_table.appendChild(result_thead);
result_table.appendChild(result_tbody);

document.getElementById('result').appendChild(result_table);
var radiobtn = document.createElement('input');
radiobtn.type = 'radio';
radiobtn.id = 'contact';
radiobtn.value = 'select';


cargo_info=new Array();
var clicked_info = [];
var clicked_item = new Array();

for (var info=0;info <10;info++){
    var list=["2022",info+"bl",info+"manageNo","des",info+" EA",info+" PLT",info+" EA",info+" -EA",info+"(EA)",info+"(적)","Remark"];
    cargo_info.push(list)
}

let row_list= new Array();
var row_1 =document.createElement('tr');
let heading_0 =document.createElement('th');
heading_0.innerHTML="";
row_list.push(heading_0);
let heading_1= document.createElement('th')
heading_1.innerText="반입일";
row_list.push(heading_1);
let heading_2= document.createElement('th');
heading_2.innerHTML= "비엘번호";
row_list.push(heading_2);
let heading_3= document.createElement('th');
heading_3.innerText= "관리번호";
row_list.push(heading_3);
let heading_4 =document.createElement('th');
heading_4.innerHTML="품명";
row_list.push(heading_4);
let heading_5 =document.createElement('th');
heading_5.innerHTML="반입총수량";
row_list.push(heading_5);
let heading_6=document.createElement('th');
heading_6.innerHTML="반입팔렛트수량";
row_list.push(heading_6);
let heading_7=document.createElement('th');
heading_7.innerHTML="팔렛드적재수량";
row_list.push(heading_7);
let heading_8=document.createElement('th');
heading_8.innerHTML="파손,검채수량";
row_list.push(heading_8);
let heading_9=document.createElement('th');
heading_9.innerHTML="현재고수량";
row_list.push(heading_9);
let heading_10=document.createElement('th');
heading_10.innerHTML="현팔렛드수량";
row_list.push(heading_10);
let heading_11=document.createElement('th');
heading_11.innerHTML="비고"
row_list.push(heading_11);

// row_1.appendChild(heading_1);
// row_1.appendChild(heading_2);
// row_1.appendChild(heading_3);
for (let value of row_list) row_1.appendChild(value);
thead.appendChild(row_1);
// result_thead.appendChild(row_1);


column_list = new Array();
var column_list_size = cargo_info.length;
for(var column_count=0;column_count<column_list_size;column_count++){
    var row_2= document.createElement('tr');
    // row_2.addEventListener('mouseover',()=>{
    //     row_2.style.backgroundColor = 'lightgray';
    //     row_2.style.cursor='pointer'
    // });
    // row_2.addEventListener('mouseout',()=>{
    //     row_2.style.backgroundColor = '';
    //     row_2.style.cursor='default'
    // });
    var radiobtn = document.createElement('input');
        radiobtn.type = 'checkbox';
        radiobtn.id = 'checked';
        radiobtn.value='selected';
        // radiobtn.onclick=function(event){
        //     if(this.checked){
        //         alert(row_2.id);
        //     }
        // };

    for(var list_count=0; list_count<11;list_count++){        
        let item_list=document.createElement("td");
        var value=cargo_info[column_count][list_count];
        item_list.innerHTML=value;
        if (list_count ==0){
            row_2.appendChild(radiobtn);
        }
        row_2.id=column_count;        
        row_2.appendChild(item_list);
    }
    // tbody.appendChild(radiobtn);    
    tbody.appendChild(row_2);
   
    // result_tbody.appendChild(row_2);
    
}
function outcargoClicked(index,item){
    // var length = result_table.rows[index].cells;
    var currentStock = parseInt(result_table.rows[index].cells[7].innerHTML.replace(/[^0-9]/g,''));
    var outcargoQty = result_table.rows[index].cells[5].innerHTML.replace(/[^0-9]/g,'');
    var stP = parseInt(item/currentStock);
    if(isNaN(stP)){
        stP = currentStock;
    }
    result_table.rows[index].cells[6].innerHTML=stP;
    let outcargo_count = result_table.rows.length;
    let outtotalQty=0;
    let outtotalPqty=0;
    for (let i=1;i<outcargo_count;i++){
        let outPqty = parseInt(result_table.rows[i].cells[6].innerHTML.replace(/[^0-9]/g,''));
        console.log(outPqty+"/"+i);
        let outqty_id = result_table.rows[i].cells[1].innerHTML+result_table.rows[i].cells[2].innerHTML+result_table.rows[i].cells[3].innerHTML+result_table.rows[i].cells[5].innerHTML+result_table.rows[i].cells[8].innerHTML;
        
        let outqty = document.getElementById(outqty_id).value;
        console.log(outqty_id+"_"+outqty);
        if(isNaN(outqty)){
            outqty=0;
        }
        outtotalQty = outtotalQty+parseInt(outqty);
        outtotalPqty = outtotalPqty+outPqty;
    } 
    document.getElementById("out_pqty").value=outtotalPqty;
    document.getElementById("out_qty").value=outtotalQty;
    
    alert(outtotalPqty);
}
function rowClicked(){
    // var table =document.getElementById('info');
    var rowList = table.rows;
    for(var i =1;i <rowList.length;i++){
        var row = rowList[i];
        var tdsNum = row.childElementCount;
        row.onclick=function(){
            return function(){
                var str = 0;
                for(var j=0;j<tdsNum;j++){
                    var row_value=this.id;
                    str =row_value;
                    
                };
                var clicked = cargo_info[str];
                var clicked_row = document.createElement('tr')
                clicked_info=[clicked[0],clicked[1],clicked[2],clicked[3],clicked[8],clicked[9],clicked[6],clicked[10]];
                var info_count=0;
                for (var k=0;k<clicked_info.length+2;k++){
                    
                    var click_item= document.createElement('td');
                    var input_item = document.createElement('input');
                    input_item.id = clicked_info[1]+clicked[2]+clicked[3]+clicked[9]+clicked[10];
                    input_item.type = "number";
                    click_item.innerHTML=clicked_info[info_count];
                    if(k==5 || k==7){
                        
                        if(k==5){
                        clicked_row.appendChild(input_item);
                        input_item.onchange = function(){
                            let index = clicked_row.rowIndex;
                            var putItem =document.getElementById(input_item.id).value;
                            console.log(input_item.id);
                            outcargoClicked(index,putItem);
                        }}else{
                            clicked_row.appendChild(click_item);
                        }                        
                        
                            // var currentStock = result_table.rows[index].cells[6].innerHTML;
                            // var outcargoQty = result_table.rows[index].cells[5].innerHTML;
                            // result_table.rows[index].cells[7].innerHTML=outcargoQty/currentStock;
                        
                    }else{
                        clicked_row.appendChild(click_item);
                        info_count = info_count+1;       
                    }                                 
                }
                // submit_btn = document.createElement('input');
                // submit_btn.type = "button";
                // clicked_row.appendChild(submit_btn);
                result_tbody.appendChild(clicked_row);
            };
        }(row)
    }
}
window.onload = rowClicked();

// result_thead.appendChild(row_1);
// result_tbody.appendChild(row_2);



