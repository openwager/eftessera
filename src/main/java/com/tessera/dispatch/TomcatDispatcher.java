package com.tessera.dispatch;

//import javax.management.*;
//
//import org.apache.catalina.ContainerServlet;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.Host;
//import org.apache.catalina.Wrapper;
//import org.apache.catalina.Engine;

/**
 * 
 * @author crawford
 *
 */

public class TomcatDispatcher
{
//	extends StandardDispatcher
//		implements ContainerServlet
//{
//	public
//	TomcatDispatcher ()
//	{
//		super (); 
//		return; 
//	}
//
//    /**                                                                                                                             
//     * The Context container associated with our web application.                                                                   
//     */
//    protected transient Context context = null;
//
//   /**                                                                                                                             
//    * The associated host.                                                                                                         
//    */
//   protected transient Host host = null;
//
//   /**                                                                                                                             
//    * MBean server.                                                                                                                
//    */
//   protected transient MBeanServer mBeanServer = null;
//
//   /**                                                                                                                             
//    * The associated deployer ObjectName.                                                                                          
//    */
//   protected ObjectName oname = null;
//
//   /**                                                                                                                             
//    * The Wrapper container associated with this servlet.                                                                          
//    */
//   protected transient Wrapper wrapper = null;
//	
//    /**                                                                                                                             
//     * Return the Wrapper with which we are associated.                                                                             
//     */
//    @Override
//    public Wrapper getWrapper() {
//
//        return (this.wrapper);
//
//    }
//
//
//    /**                                                                                                                             
//     * Set the Wrapper with which we are associated.                                                                                
//     *                                                                                                                              
//     * @param wrapper The new wrapper                                                                                               
//     */
//    @Override
//    public void setWrapper(Wrapper wrapper) {
//
//        this.wrapper = wrapper;
//        if (wrapper == null) {
//            context = null;
//            host = null;
//            oname = null;
//        } else {
//            context = (Context) wrapper.getParent();
//            host = (Host) context.getParent();
//            Engine engine = (Engine) host.getParent();
//            String name = engine.getName() + ":type=Deployer,host=" + host.getName();
//            try {
//                oname = new ObjectName(name);
//            } catch (Exception e) {
//                e.printStackTrace ();
//            }
//        }
//
//        // Retrieve the MBean server                                                                                                
////        mBeanServer = Registry.getRegistry(null, null).getMBeanServer();
//        return; 
//    }
}

// EOF
