/* Generated By:JJTree: Do not edit this line. ASTPhpPage.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTPhpPage extends SimpleNode {
  public ASTPhpPage(int id) {
    super(id);
  }

  public ASTPhpPage(PHP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(PHPVisitor visitor, int branchAdd) {

    visitor.visit(this, branchAdd);
  }
}
/* JavaCC - OriginalChecksum=70dc63c153693df8e91d268d945f8365 (do not edit this line) */
