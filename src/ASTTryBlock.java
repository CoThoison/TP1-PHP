/* Generated By:JJTree: Do not edit this line. ASTTryBlock.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTTryBlock extends SimpleNode {
  public ASTTryBlock(int id) {
    super(id);
  }

  public ASTTryBlock(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, int branchAdd) {

    visitor.visit(this, branchAdd);
  }
}
/* JavaCC - OriginalChecksum=a1b7c57a88e83874da130c55f0125049 (do not edit this line) */
