/*
 * Copyright 2020 Marco Bignami.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unknowndomain.alea.systems.lexarcana2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.random.dice.DiceBuilder;
import net.unknowndomain.alea.random.dice.DiceN;
import net.unknowndomain.alea.roll.GenericResult;
import net.unknowndomain.alea.roll.GenericRoll;

/**
 *
 * @author journeyman
 */
public class LexArcana2Roll implements GenericRoll
{
    
    private final List<DiceN> diceList;
    private final Set<LexArcana2Modifiers> mods;
    private final Locale lang;
    
    
    public LexArcana2Roll(Integer first, Integer second, Integer third, Locale lang, LexArcana2Modifiers ... mod)
    {
        this(first, second, third, lang, Arrays.asList(mod));
    }
    
    public LexArcana2Roll(Integer first, Integer second, Integer third, Locale lang, Collection<LexArcana2Modifiers> mod)
    {
        this.mods = new HashSet<>();
        if (mod != null)
        {
            this.mods.addAll(mod);
        }
        List<DiceN> tmp = new ArrayList<>();
        tmp.add(DiceBuilder.parseDice(first));
        if ((second != null) && (second > 0))
        {
            tmp.add(DiceBuilder.parseDice(second));
        }
        if ((third != null) && (third > 0))
        {
            tmp.add(DiceBuilder.parseDice(third));
        }
        this.diceList = Collections.unmodifiableList(tmp);
        this.lang = lang;
    }
    
    @Override
    public GenericResult getResult()
    {
        LexArcana2Results results = buildResults();
        results.setVerbose(mods.contains(LexArcana2Modifiers.VERBOSE));
        results.setPoolSize(this.diceList.size());
        results.setLang(lang);
        return results;
    }
    
    private LexArcana2Results buildResults()
    {
        List<SingleResult<Integer>> res = new ArrayList<>(diceList.size());
        boolean fateRoll = true;
        while(fateRoll)
        {
            for (DiceN dn : diceList)
            {
                SingleResult<Integer> r = dn.nextResult().get();
                fateRoll = fateRoll && (r.getValue() == dn.getMaxResult());
                res.add(r);
            }
        }
        LexArcana2Results results = new LexArcana2Results(res);
        return results;
    }
    
    
}
