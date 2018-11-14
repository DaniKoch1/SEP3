using System;
using System.Collections.Generic;
using Controllers.Connections;
using Models;
using Xunit;

namespace Tests
{
    public class DatabaseProxyTest : DatabaseProxy
    {
        //Run only with DB running
        [Fact]
        public void SearchTest()
        {
            var list = new List<Book>
            {
                //id not checked in db, just put in order to build
                new Book("Lord of the Rings: Fellowship of the Ring", "J.R.R Tolkien", 1954, "978-83-8116-1",
                    Category.Fantasy)
            };
            Assert.Equal(list, Search("Tolkien"));
        }
        
        [Fact]
        public void GetResponsStatusTest()
        {
            string response =
                "{\"status\":\"OK\",\"content\":[{\"isbn\":\"978-83-8116-1\",\"title\":\"Lord of the Rings: Fellowship of the Ring\",\"author\":\"J.R.R Tolkien\",\"year\":1954,\"category\":\"Fantasy\"}]}";
            Assert.Equal(GetResponseStatus(response),ResponseStatus.OK);
        }
        
        [Fact]
        public void GetContentTestListOfBooks()
        {
            var list = new List<Book>
            {
                //id not checked in db, just put in order to build
                new Book("Lord of the Rings: Fellowship of the Ring", "J.R.R Tolkien", 1954, "978-83-8116-1",
                    Category.Fantasy)
            };
            string response =
                "{\"status\":\"OK\",\"content\":[{\"isbn\":\"978-83-8116-1\",\"title\":\"Lord of the Rings: Fellowship of the Ring\",\"author\":\"J.R.R Tolkien\",\"year\":1954,\"category\":\"Fantasy\"}]}";
            Assert.Equal(GetContent<List<Book>>(response),list);
        }
        
        [Fact]
        public void GetContentTestError()
        {
            var msg = "Error";
            string response =
                "{\"status\":\"Error\",\"content\":\"Error\"}";
            Assert.Equal(GetContent<string>(response),msg);
        }

        [Fact]
        public void AddBookTest()
        {
            var book = new Book("Effective Java","Joshua Bloch",2017,"978-0134685991", Category.Science);
            CreateBook(book);
        }
        
        [Fact]
        public void DeleteBookTest()
        {
           DeleteBook("97bb0a09-330e-4939-9e75-cc39d9000240");
        }
    }
}