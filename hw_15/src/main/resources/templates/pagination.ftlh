<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Guest Book</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <#if booksElements!=0>
        <table class="table table-success table-striped" border="1px">
            <h2>Guest Book</h2>
            <thead>
                <tr class="w3-red">
                    <th style="text-align:center"> name </th>
                    <th style="text-align:center"> message </th>
                    <th style="text-align:center"> rating </th>
                </tr>
            </thead>
            <tbody>
                <#list books.content as book>
                    <tr>
                        <td style="text-align:center"> ${book.name} </td>
                        <td style="text-align:center"> ${book.message} </td>
                        <td style="text-align:center"> ${book.rating} </td>
                    </tr>
                </#list>
            </tbody>
        </table>

        <#if booksPages gt 7>
            <#assign
                pageNumber = currentPage + 1
                pages = booksPages

                head = (pageNumber > 4)?then([1, -1], [1, 2, 3])
                tail = (pageNumber < pages - 3)?then([-1, pages], [pages - 2, pages - 1, pages])
                pagesBefore = (pageNumber > 4 && pageNumber < pages -1)?then([pageNumber - 2, pageNumber - 1], [])
                pagesAfter = (pageNumber > 2 && pageNumber < pages - 3)?then([pageNumber + 1, pageNumber + 2], [])

                pages = head + pagesBefore
                    + (pageNumber > 3 && pageNumber < pages -2)?then([pageNumber], [])
                    + pagesAfter + tail
            >
        <#else>
            <#assign pages = 1..booksPages>
        </#if>

        <#if booksPages==1>
            <a href="/guest-book/pagination?page=0&itemsPerPage=${itemsPerPage}">1</a>
        <#elseif currentPage==0>
            <ul class="pagination">
                <#include 'pages/numbers.ftlh'>
                <#include 'pages/next.ftlh'>
                <#include 'pages/last.ftlh'>
            </ul>
        <#elseif currentPage==booksPages-1>
            <ul class="pagination">
                <#include 'pages/first.ftlh'>
                <#include 'pages/prev.ftlh'>
                <#include 'pages/numbers.ftlh'>
            </ul>
        <#else>
            <ul class="pagination">
                <#include 'pages/first.ftlh'>
                <#include 'pages/prev.ftlh'>
                <#include 'pages/numbers.ftlh'>
                <#include 'pages/next.ftlh'>
                <#include 'pages/last.ftlh'>
            </ul>
        </#if>
    </#if>
</body>
</html>