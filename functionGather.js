
var XLSX = require("xlsx");

function putDoData(exPath){
    exPath ="deliveryOrder.xlsx"
    let workbook = XLSX.readFile(exPath);
    let sheet_name = workbook.SheetNames;
    let worksheet = workbook.Sheets[sheet_name[0]];
    const options={
        defval:"",
        range:"A7:I32",
        blankrows:false,
        raw:false,
        header:0
    }
    //let data = XLSX.utils.sheet_to_json(worksheet,options);
    //console.log(data);
    var cell = "C7";
    XLSX.utils.sheet_set_cell(worksheet,"C7","Test");
    
    //XLSX.utils.sheet_add_aoa(worksheet,["Test"],{origin:"C7"})
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');
    
    XLSX.writeFile(workbook, exPath, { bookType: 'xlsx' })
    
}
putDoData("koaca");
