import { initializeApp } from "https://www.gstatic.com/firebasejs/9.18.0/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.18.0/firebase-analytics.js";
import { getDatabase,ref,set } from "https://www.gstatic.com/firebasejs/9.18.0/firebase-database.js";
const firebaseConfig = {
    apiKey: "AIzaSyDLzmZyt5nZwCk98iZ6wi01y7Jxio1ppZQ",
    authDomain: "fine-bondedwarehouse.firebaseapp.com",
    databaseURL: "https://fine-bondedwarehouse-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "fine-bondedwarehouse",
    storageBucket: "fine-bondedwarehouse.appspot.com",
    messagingSenderId: "415417723331",
    appId: "1:415417723331:web:15212f190062886281b576",
    measurementId: "G-SWBR4359JQ"
};


const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
const database_f = getDatabase();


// function test(value) {
//     const db = getDatabase();
//     set(ref(db, 'users/' ), {
//         test: value
//     });
// }
// test("test");
export {database_f};
// Import the functions you need from the SDKs you need