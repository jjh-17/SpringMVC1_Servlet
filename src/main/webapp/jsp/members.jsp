<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.membership.domain.member.Member" %>
<%@ page import="hello.servlet.membership.domain.repository.MemberRepository" %>

<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<body>
<a href="/index.html">메인</a>
<table>
    <thead>
        <th>id</th>
        <th>username</th>
        <th>age</th>
    </thead>

    <tbody>
    <%
        for (Member member : members) {
            out.write("\t<tr>\n");
            out.write("\t\t<td>" + member.getId() + "</td>\n");
            out.write("\t\t<td>" + member.getUsername() + "</td>\n");
            out.write("\t\t<td>" + member.getAge() + "</td>\n");
            out.write("\t</tr>\n\n");
        }
    %>
    </tbody>
</table>
</body>
</html>
