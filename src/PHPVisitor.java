/* Generated By:JavaCC: Do not edit this line. PHPVisitor.java Version 7.0.14 */
public interface PHPVisitor
{
  public void visit(SimpleNode node, Object data);
  public void visit(ASTPhpPage node, Object data);
  public void visit(ASTHtmlBlock node, Object data);
  public void visit(ASTStatement node, Object data);
  public void visit(ASTThrowStatement node, Object data);
  public void visit(ASTTryBlock node, Object data);
  public void visit(ASTEndOfStatement node, Object data);
  public void visit(ASTEmbeddedHtml node, Object data);
  public void visit(ASTDefineStatement node, Object data);
  public void visit(ASTLabeledStatement node, Object data);
  public void visit(ASTExpressionStatement node, Object data);
  public void visit(ASTCompoundStatement node, Object data);
  public void visit(ASTSelectionStatement node, Object data);
  public void visit(ASTIterationStatement node, Object data);
  public void visit(ASTJumpStatement node, Object data);
  public void visit(ASTParameterList node, Object data);
  public void visit(ASTParameter node, Object data);
  public void visit(ASTClassDeclaration node, Object data);
  public void visit(ASTClassMembers node, Object data);
  public void visit(ASTMemberDeclaration node, Object data);
  public void visit(ASTInterfaceDeclaration node, Object data);
  public void visit(ASTInterfaceMembers node, Object data);
  public void visit(ASTInterfaceMember node, Object data);
  public void visit(ASTIncludeStatement node, Object data);
  public void visit(ASTEchoStatement node, Object data);
  public void visit(ASTExpression node, Object data);
  public void visit(ASTLogicalTextOrExpression node, Object data);
  public void visit(ASTLogicalTextXorExpression node, Object data);
  public void visit(ASTLogicalTextAndExpression node, Object data);
  public void visit(ASTAssignmentExpression node, Object data);
  public void visit(ASTAssignmentOperator node, Object data);
  public void visit(ASTConditionalExpression node, Object data);
  public void visit(ASTLogical_Or_Expression node, Object data);
  public void visit(ASTLogical_And_Expression node, Object data);
  public void visit(ASTBitwiseOrExpression node, Object data);
  public void visit(ASTBitwiseXorExpression node, Object data);
  public void visit(ASTBitwiseAndExpression node, Object data);
  public void visit(ASTEqualityExpression node, Object data);
  public void visit(ASTRelationalExpression node, Object data);
  public void visit(ASTShiftExpression node, Object data);
  public void visit(ASTAdditiveExpression node, Object data);
  public void visit(ASTMultiplicativeExpression node, Object data);
  public void visit(ASTCastExpression node, Object data);
  public void visit(ASTUnaryExpression node, Object data);
  public void visit(ASTPrefixIncDecExpression node, Object data);
  public void visit(ASTPostfixIncDecExpression node, Object data);
  public void visit(ASTInstanceOfExpression node, Object data);
  public void visit(ASTPostfixExpression node, Object data);
  public void visit(ASTPrimaryExpression node, Object data);
  public void visit(ASTArray node, Object data);
  public void visit(ASTClassInstantiation node, Object data);
  public void visit(ASTVariable node, Object data);
  public void visit(ASTArgumentExpressionList node, Object data);
  public void visit(ASTConstant node, Object data);
  public void visit(ASTString node, Object data);
  public void visit(ASTDoubleStringLiteral node, Object data);
  public void visit(ASTVisibility node, Object data);
}
/* JavaCC - OriginalChecksum=f96c03d6c5f143795d69fa53ccaafad5 (do not edit this line) */
