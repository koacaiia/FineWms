// Import the functions you need from the SDKs you need
var initializeApp = require('firebase/app')
var getDatabase = require('firebase/database')
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
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

// Initialize Firebase
const app = initializeApp(firebaseConfig);
var database = getDatabase(app);
function firebasecheck(value){
    database.ref("test/").set(value);
}
firebasecheck("test");