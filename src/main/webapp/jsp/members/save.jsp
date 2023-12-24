<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.membership.domain.member.Member" %>
<%@ page import="hello.servlet.membership.domain.repository.MemberRepository" %>

<%
    //자바 코드 작성 범위
    //request, response 사용 가능
    System.out.println("save.jsp");

    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    Member member = new Member(username, age);

    System.out.println("member = " + member);
    memberRepository.save(member);
%>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
