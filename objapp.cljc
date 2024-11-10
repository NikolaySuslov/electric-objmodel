(ns electric-tutorial.objapp
  (:require [hyperfiddle.electric3 :as e]
            [hyperfiddle.electric-dom3 :as dom]
            [hyperfiddle.incseq :as i]
            [missionary.core :as m]))

#?(:cljs
   (defn vtable-add-method! [met name method]
     (met name {} method)))

(e/defn Objmodel []
  (e/client

    (let [!ovt  (i/spine)
          ovt (e/join !ovt)

          !vvt  (i/spine)
          vvt (e/join !vvt)

          vtable-vt (e/join (:vtable ovt))]

      (e/letfn [(Bind [o mname]
                  (if (= mname :lookup)
                    (VtableLookup o mname)
                    (Send (e/join (:vtable o)) :lookup mname)))

                (Send [o mname & args]
                  (let [closure (Bind o mname)]
                    (println "DO!" closure " " mname)
                    (e/Apply closure (flatten [o args]))))

                (VtableLookup [self mname]
                  (let [method  (e/amb
                                  (e/for [[i o] (e/join (:methods self))]
                                    (e/When (= mname i) o)))

                        parent (:parent self)]

                    (if (e/Some? method)
                      method
                      (if (some? parent)
                        (Send (e/join parent) :lookup mname)
                        (do
                          (println "No Parent! for " mname)
                          (e/amb))))))]

        (!vvt :vvt {} {:methods (i/spine) :parent !ovt :vtable !vvt})
        (!ovt :ovt {} {:methods (i/spine) :parent nil :vtable !vvt})

        (vtable-add-method! (:methods vtable-vt) :lookup [:lookup VtableLookup])
        (vtable-add-method! (:methods vtable-vt) :msg1 [:msg1 (e/fn [o & args]
                                                                (println "OK MSG1! " args)
                                                                (Send o :msg2 "send from MSG1"))])

        (vtable-add-method! (:methods vtable-vt) :msg2 [:msg2 (e/fn [o & args]
                                                                (println "OK MSG2! " args))])


        ;;(Send ovt :msg2 "msg2 with args")

        (dom/button
          (let [spend (e/Token (dom/On "click"))]
            (when spend
              (spend
                (Send ovt :msg1 "msg1 with args")))
            (dom/text "CLICK")))

        (e/for [[i o] (e/join (:methods vtable-vt))]
          (dom/div
            (dom/text i " - " o)))))))

(e/defn Objapp []
  (e/client
    (Objmodel)))